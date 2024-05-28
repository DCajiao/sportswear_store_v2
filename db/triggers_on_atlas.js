// TRIGGERS ON ATLAS
// This file contains the triggers that are used in the Atlas database.

// Trigger_Compras: On Insert
// - Validate if user_account exists
// - Validate if product_id exists and Product_type is Item or Package
// - Validate product availability
// - Validate if design_id exists
// - Validate product availability
// - Update new product quantities available per size
// - Calculate TotalCost considering active promotions

exports = async function (changeEvent) {
    const docId = changeEvent.documentKey._id;
    const serviceName = "Cluster1";
    const database = "sportswear_store";

    const comprasCollection = context.services.get(serviceName).db(database).collection("Compras");
    const personasCollection = context.services.get(serviceName).db(database).collection("Personas");
    const productosCollection = context.services.get(serviceName).db(database).collection("Productos");
    const promocionesCollection = context.services.get(serviceName).db(database).collection("Promociones");

    try {
        if (changeEvent.operationType === "insert") {
            const compra = changeEvent.fullDocument;

            // Validar si cuenta_usuario existe
            const cuentaUsuario = await personasCollection.findOne({ "cuentas.usuario": compra.cuenta_usuario });
            if (!cuentaUsuario) {
                const error = `El usuario ${compra.cuenta_usuario} no existe.`;
                console.error("Error en el trigger de compras:", error);
                await comprasCollection.deleteOne({ "_id": docId });
                throw new Error(error);
            }

            // Validar si producto_id existe y tipoProducto es Articulo o Paquete
            for (const detalle of compra.detallesCompra) {
                const producto = await productosCollection.findOne({ "_id": detalle.producto_id, "tipoProducto": { "$in": ["Articulo", "Paquete"] } });
                if (!producto) {
                    const error = `El producto con ID ${detalle.producto_id} no existe o no es de tipo Articulo o Paquete.`;
                    console.error("Error en el trigger de compras:", error);
                    await comprasCollection.deleteOne({ "_id": docId });
                    throw new Error(error);
                }

                // Validar disponibilidad de productos
                const especificacion = producto.especificacionesArticulo.find(e => e.talla === detalle.talla);
                if (!especificacion || especificacion.cantidad < detalle.cantidad) {
                    const error = `La cantidad deseada del producto ${producto.descripcion} en talla ${detalle.talla} no está disponible.`;
                    console.error("Error en el trigger de compras:", error);
                    await comprasCollection.deleteOne({ "_id": docId });
                    throw new Error(error);
                }
            }

            // Calcular el costoTotal considerando promociones activas
            let costoTotal = 0;
            const currentDate = new Date();

            for (const detalle of compra.detallesCompra) {
                const producto = await productosCollection.findOne({ "_id": detalle.producto_id });

                // Verificar si hay una promoción activa para el producto
                const promocion = await promocionesCollection.findOne({
                    "productos.producto_id": detalle.producto_id,
                    "fechaInicio": { "$lte": currentDate },
                    "fechaFin": { "$gte": currentDate }
                });

                let precioProducto = producto.precio;

                if (promocion) {
                    precioProducto = precioProducto * (1 - promocion.descuento);
                }

                costoTotal += precioProducto * detalle.cantidad;
            }

            compra.costoTotal = costoTotal;

            // Tomar la fecha automáticamente
            compra.fechaCompra = new Date();
            
            // Actualizar la compra con los datos calculados
            await comprasCollection.updateOne({ "_id": docId }, { "$set": { "costoTotal": costoTotal, "fechaCompra": compra.fechaCompra } });

            // Actualizar nuevas cantidades de productos disponibles por talla y recalcular cantidadArticulo
            for (const detalle of compra.detallesCompra) {
                // Actualizar cantidad de productos disponibles por talla
                await productosCollection.updateOne(
                    { "_id": detalle.producto_id, "especificacionesArticulo.talla": detalle.talla },
                    { "$inc": { "especificacionesArticulo.$.cantidad": -detalle.cantidad } }
                );
            }

            // Recalcular cantidadArticulo después de actualizar todas las tallas
            for (const detalle of compra.detallesCompra) {
                const productoActualizado = await productosCollection.findOne({ "_id": detalle.producto_id });
                const nuevaCantidad = productoActualizado.especificacionesArticulo.reduce((acc, espec) => acc + espec.cantidad, 0);

                // Actualizar cantidadArticulo
                await productosCollection.updateOne(
                    { "_id": detalle.producto_id },
                    { "$set": { "cantidadArticulo": nuevaCantidad } }
                );
            }
        }
    } catch (err) {
        console.error("Error en el trigger de compras:", err.message);

        // Si ocurre un error, eliminar el documento insertado
        await comprasCollection.deleteOne({ "_id": docId });

        // Lanza la excepción para que el error se muestre en la consola
        throw new Error(`Error en el trigger de compras: ${err.message}`);
    }
};

// ----------------------------------------------

// Trigger_Resenas: On Insert
// - Validate if user_account exists
// - Validate if product_id exists
// - Update date automatically

exports = async function (changeEvent) {
    const docId = changeEvent.documentKey._id;
    const serviceName = "Cluster1";
    const database = "sportswear_store";
    const reseñasCollection = context.services.get(serviceName).db(database).collection("Reseñas");
    const personasCollection = context.services.get(serviceName).db(database).collection("Personas");
    const productosCollection = context.services.get(serviceName).db(database).collection("Productos");

    try {
        if (changeEvent.operationType === "insert") {
            const reseña = changeEvent.fullDocument;

            // Validar si cuenta_usuario existe
            const cuentaUsuario = await personasCollection.findOne({ "cuentas.usuario": reseña.cuenta_usuario });
            if (!cuentaUsuario) {
                const error = `El usuario ${reseña.cuenta_usuario} no existe.`;
                console.error("Error en el trigger de reseñas:", error);
                await reseñasCollection.deleteOne({ "_id": docId });
                throw new Error(error);
            }

            // Validar si producto_id existe
            const producto = await productosCollection.findOne({ "_id": reseña.producto_id });
            if (!producto) {
                const error = `El producto con ID ${reseña.producto_id} no existe.`;
                console.error("Error en el trigger de reseñas:", error);
                await reseñasCollection.deleteOne({ "_id": docId });
                throw new Error(error);
            }

            // Tomar la fecha automáticamente
            reseña.fecha = new Date();

            // Actualizar la reseña con la fecha actual
            await reseñasCollection.updateOne({ "_id": docId }, { "$set": { "fecha": reseña.fecha } });
        }
    } catch (err) {
        console.error("Error en el trigger de reseñas:", err.message);

        // Si ocurre un error, eliminar el documento insertado
        await reseñasCollection.deleteOne({ "_id": docId });

        // Lanza la excepción para que el error se muestre en la consola
        throw new Error(`Error en el trigger de reseñas: ${err.message}`);
    }
};

// ----------------------------------------------

// Trigger_Envios: On Insert
// - Validate if compra_id exists
// - Validate if lugar_id exists

exports = async function (changeEvent) {
    const docId = changeEvent.documentKey._id;
    const serviceName = "Cluster1";
    const database = "sportswear_store";
    const enviosCollection = context.services.get(serviceName).db(database).collection("Envios");
    const comprasCollection = context.services.get(serviceName).db(database).collection("Compras");
    const lugaresCollection = context.services.get(serviceName).db(database).collection("Lugares");

    try {
        if (changeEvent.operationType === "insert") {
            const envio = changeEvent.fullDocument;

            // Validar si compra_id existe
            const compra = await comprasCollection.findOne({ "_id": envio.compra_id });
            if (!compra) {
                const error = `La compra con ID ${envio.compra_id} no existe.`;
                console.error("Error en el trigger de envios:", error);
                await enviosCollection.deleteOne({ "_id": docId });
                throw new Error(error);
            }

            // Validar si lugar_id existe
            const lugar = await lugaresCollection.findOne({ "_id": envio.lugar_id });
            if (!lugar) {
                const error = `El lugar con ID ${envio.lugar_id} no existe.`;
                console.error("Error en el trigger de envios:", error);
                await enviosCollection.deleteOne({ "_id": docId });
                throw new Error(error);
            }
        }
    } catch (err) {
        console.error("Error en el trigger de envios:", err.message);

        // Si ocurre un error, eliminar el documento insertado
        await enviosCollection.deleteOne({ "_id": docId });

        // Lanza la excepción para que el error se muestre en la consola
        throw new Error(`Error en el trigger de envios: ${err.message}`);
    }
};

// ----------------------------------------------

// Trigger_Eliminacion_Compra: On Insert
// - Delete associated shipping record
// - Return product availability
// - Recalculate cantidadArticulo

exports = async function (changeEvent) {
    const docId = changeEvent.documentKey._id;
    const serviceName = "Cluster1";
    const database = "sportswear_store";
    const enviosCollection = context.services.get(serviceName).db(database).collection("Envios");
    const productosCollection = context.services.get(serviceName).db(database).collection("Productos");
    const comprasCollection = context.services.get(serviceName).db(database).collection("Compras");

    try {
        if (changeEvent.operationType === "delete") {
            // Eliminar el registro de envío asociado
            await enviosCollection.deleteOne({ "compra_id": docId });

            // Obtener los detalles de la compra eliminada antes de eliminarla
            const compraEliminada = changeEvent.fullDocumentBeforeChange;

            if (compraEliminada && compraEliminada.detallesCompra) {
                for (const detalle of compraEliminada.detallesCompra) {
                    // Retornar disponibilidad de productos
                    await productosCollection.updateOne(
                        { "_id": detalle.producto_id, "especificacionesArticulo.talla": detalle.talla },
                        { "$inc": { "especificacionesArticulo.$.cantidad": detalle.cantidad } }
                    );

                    // Recalcular la cantidadArticulo
                    const productoActualizado = await productosCollection.findOne({ "_id": detalle.producto_id });
                    const nuevaCantidad = productoActualizado.especificacionesArticulo.reduce((acc, espec) => acc + espec.cantidad, 0);

                    // Actualizar cantidadArticulo
                    await productosCollection.updateOne(
                        { "_id": detalle.producto_id },
                        { "$set": { "cantidadArticulo": nuevaCantidad } }
                    );
                }
            }
        }
    } catch (err) {
        console.error("Error en el trigger de eliminación de compras:", err.message);
        throw new Error(`Error en el trigger de eliminación de compras: ${err.message}`);
    }
};

// ----------------------------------------------

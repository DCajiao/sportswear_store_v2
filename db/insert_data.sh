// This script is used to insert data into the database
// But it has only one record for testing

// Insert data in the Lugares collection
db.Lugares.insertOne({
    "_id": ObjectId(),
    "nombre": "Santiago de Cali",
    "subLugar_id": 1
});

// Insert data in the Personas collection
db.Personas.insertOne({
    "_id": ObjectId(),
    "nombre": "Juan Pérez",
    "correo": "juan@example.com",
    "telefono": "123456789",
    "genero": "Hombre",
    "edad": 30,
    "lugar_id": ObjectId(),
    "cuentas": [{
        "usuario": "juanperez",
        "contraseña": "contraseña123",
        "tipoCuenta": "Cliente"
    }]
});

// Insert data in the Productos collection
db.Productos.insertOne({
    "_id": ObjectId(),
    "precio": 39.99,
    "imagenProducto": "imagen1.jpg",
    "tipoProducto": "Articulo",
    "descripcion": "Camiseta de algodón",
    "CategoriaArticulo": "Camiseta",
    "seccionArticulo": "Hombre",
    "cantidadArticulo": 100,
    "especificacionesArticulo": [{
        "cantidad": 50,
        "talla": "M"
    },{
        "cantidad": 50,
        "talla": "L"
    }]
});

// Insert data in the Compras collection
db.Compras.insertOne({
    "_id": ObjectId(),
    "cuenta_usuario": "juanperez",
    "costoTotal": 79.98,
    "fechaCompra": new Date(),
    "detallesCompra": [{
        "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id, // Para que traiga el objectId de la camiseta
        "cantidad": 2,
        "talla": "M",
        "personalizacion": {}
    }]
});

// Insert data in the Envío collection
db.Envios.insertOne({
    "_id": ObjectId(),
    "direccion": "Calle Principal 123",
    "fechaEnvio": new Date(),
    "codigoPostal": 12345,
    "compra_id": db.Compras.findOne()._id,
    "lugar_id": db.Lugares.findOne({ "nombre": "Santiago de Cali" })._id // Para que traiga el objectId de Santiago de Cali
});

// Insert data in the Reseñas collection
db.Reseñas.insertOne({
    "_id": ObjectId(),
    "comentario": "¡Excelente producto!",
    "valoracion": 5,
    "cuenta_usuario": db.Personas.findOne({ "nombre": "Juan Pérez" })._id,
    "fecha": new Date(),
    "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id
});

// Insert data in the Promociones collection
db.Promociones.insertOne({
    "_id": ObjectId(),
    "fechaInicio": new Date(),
    "fechaFin": new Date(Date.now() + (30 * 24 * 60 * 60 * 1000)), // 30 días después
    "productos": [{
        "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id
    }],
    "descuento": 10 // 10% de descuento
});

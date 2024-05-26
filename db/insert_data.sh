// This script is used to insert data into the database

// Insert data in the Lugares collection
db.Lugares.insertOne({ "_id": ObjectId(), "nombre": "Colombia"})

db.Lugares.insertMany([
    { "_id": ObjectId(), "nombre": "Santiago de Cali", "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Bogotá",           "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Medellín",         "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Barranquilla",     "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Cartagena",        "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Bucaramanga",      "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Pereira",          "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Manizales",        "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Cúcuta",           "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id },
    { "_id": ObjectId(), "nombre": "Santa Marta",      "subLugar_id": db.Lugares.findOne({ "nombre": "Colombia" })._id }
]);



// Insert data in the Personas collection
db.Personas.insertMany([
    { "_id": ObjectId(), "nombre": "Juan Pérez",      "correo": "juan@example.com",   "telefono": "123456789", "genero": "Hombre", "edad": 30, "lugar_id": db.Lugares.findOne({ "nombre": "Santiago de Cali" })._id, "cuentas": [{ "usuario": "juanperez",      "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Ana López",       "correo": "ana@example.com",    "telefono": "987654321", "genero": "Mujer",  "edad": 25, "lugar_id": db.Lugares.findOne({ "nombre": "Bogotá" })._id,           "cuentas": [{ "usuario": "analopez",       "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Carlos García",   "correo": "carlos@example.com", "telefono": "123123123", "genero": "Hombre", "edad": 35, "lugar_id": db.Lugares.findOne({ "nombre": "Medellín" })._id,         "cuentas": [{ "usuario": "carlosgarcia",   "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Luisa Martínez",  "correo": "luisa@example.com",  "telefono": "321321321", "genero": "Mujer",  "edad": 28, "lugar_id": db.Lugares.findOne({ "nombre": "Barranquilla" })._id,     "cuentas": [{ "usuario": "luisamartinez",  "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Pedro Rodríguez", "correo": "pedro@example.com",  "telefono": "456456456", "genero": "Hombre", "edad": 40, "lugar_id": db.Lugares.findOne({ "nombre": "Cartagena" })._id,        "cuentas": [{ "usuario": "pedrorodriguez", "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Marta Díaz",      "correo": "marta@example.com",  "telefono": "789789789", "genero": "Mujer",  "edad": 33, "lugar_id": db.Lugares.findOne({ "nombre": "Bucaramanga" })._id,      "cuentas": [{ "usuario": "martadiaz",      "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Jorge Jiménez",   "correo": "jorge@example.com",  "telefono": "654654654", "genero": "Hombre", "edad": 37, "lugar_id": db.Lugares.findOne({ "nombre": "Pereira" })._id,          "cuentas": [{ "usuario": "jorgejimenez",   "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Elena Torres",    "correo": "elena@example.com",  "telefono": "987654322", "genero": "Mujer",  "edad": 29, "lugar_id": db.Lugares.findOne({ "nombre": "Manizales" })._id,        "cuentas": [{ "usuario": "elenatorres",    "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Miguel Rojas",    "correo": "miguel@example.com", "telefono": "123987456", "genero": "Hombre", "edad": 45, "lugar_id": db.Lugares.findOne({ "nombre": "Cúcuta" })._id,           "cuentas": [{ "usuario": "miguelrojas",    "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] },
    { "_id": ObjectId(), "nombre": "Laura Mendoza",   "correo": "laura@example.com",  "telefono": "456123789", "genero": "Mujer",  "edad": 27, "lugar_id": db.Lugares.findOne({ "nombre": "Santa Marta" })._id,      "cuentas": [{ "usuario": "lauramendoza",   "contraseña": "contraseña123", "tipoCuenta": "Cliente" }] }
]);

// Insert data in the Productos collection
db.Productos.insertMany([
    { "_id": ObjectId(), "precio": 39.99, "imagenProducto": "imagen1.jpg",  "tipoProducto": "Articulo", "descripcion": "Camiseta de algodón",   "CategoriaArticulo": "Camiseta",    "seccionArticulo": "Hombre", "cantidadArticulo": 100, "especificacionesArticulo": [{ "cantidad": 50,  "talla": "M" }, { "cantidad": 50, "talla": "L" }] },
    { "_id": ObjectId(), "precio": 59.99, "imagenProducto": "imagen2.jpg",  "tipoProducto": "Articulo", "descripcion": "Pantaloneta deportiva", "CategoriaArticulo": "Pantaloneta", "seccionArticulo": "Hombre", "cantidadArticulo": 80,  "especificacionesArticulo": [{ "cantidad": 40,  "talla": "M" }, { "cantidad": 40, "talla": "L" }] },
    { "_id": ObjectId(), "precio": 49.99, "imagenProducto": "imagen3.jpg",  "tipoProducto": "Articulo", "descripcion": "Gorra de béisbol",      "CategoriaArticulo": "Gorra",       "seccionArticulo": "Mujer",  "cantidadArticulo": 50,  "especificacionesArticulo": [{ "cantidad": 50,  "talla": "Única" }] },
    { "_id": ObjectId(), "precio": 79.99, "imagenProducto": "imagen4.jpg",  "tipoProducto": "Articulo", "descripcion": "Zapatillas deportivas", "CategoriaArticulo": "Zapatillas",  "seccionArticulo": "Hombre", "cantidadArticulo": 60,  "especificacionesArticulo": [{ "cantidad": 30,  "talla": "42" }, { "cantidad": 30, "talla": "43" }] },
    { "_id": ObjectId(), "precio": 29.99, "imagenProducto": "imagen5.jpg",  "tipoProducto": "Articulo", "descripcion": "Calcetines de algodón", "CategoriaArticulo": "Calcetines",  "seccionArticulo": "Nino",   "cantidadArticulo": 200, "especificacionesArticulo": [{ "cantidad": 100, "talla": "M" }, {  "cantidad": 100, "talla": "L" }] },
    { "_id": ObjectId(), "precio": 69.99, "imagenProducto": "imagen6.jpg",  "tipoProducto": "Articulo", "descripcion": "Polo deportiva",        "CategoriaArticulo": "Polo",        "seccionArticulo": "Mujer",  "cantidadArticulo": 70,  "especificacionesArticulo": [{ "cantidad": 35,  "talla": "M" }, {  "cantidad": 35, "talla": "L" }] },
    { "_id": ObjectId(), "precio": 99.99, "imagenProducto": "imagen7.jpg",  "tipoProducto": "Articulo", "descripcion": "Gorra deportiva",       "CategoriaArticulo": "Gorra",       "seccionArticulo": "Hombre", "cantidadArticulo": 40,  "especificacionesArticulo": [{ "cantidad": 40,  "talla": "Única" }] },
    { "_id": ObjectId(), "precio": 19.99, "imagenProducto": "imagen8.jpg",  "tipoProducto": "Articulo", "descripcion": "Zapatillas de yoga",    "CategoriaArticulo": "Zapatillas",  "seccionArticulo": "Hombre", "cantidadArticulo": 150, "especificacionesArticulo": [{ "cantidad": 150, "talla": "Única" }] },
    { "_id": ObjectId(), "precio": 89.99, "imagenProducto": "imagen9.jpg",  "tipoProducto": "Articulo", "descripcion": "Pantaloneta de yoga",   "CategoriaArticulo": "Pantaloneta", "seccionArticulo": "Mujer",  "cantidadArticulo": 90,  "especificacionesArticulo": [{ "cantidad": 45,  "talla": "S" }, {  "cantidad": 45, "talla": "M" }] },
    { "_id": ObjectId(), "precio": 39.99, "imagenProducto": "imagen10.jpg", "tipoProducto": "Articulo", "descripcion": "Camiseta con capucha",  "CategoriaArticulo": "Camiseta",    "seccionArticulo": "Hombre", "cantidadArticulo": 110, "especificacionesArticulo": [{ "cantidad": 55,  "talla": "L" }, {  "cantidad": 55, "talla": "XL" }] }
]);

// Insert Diseño in the Productos collection
db.Productos.insertOne(
    { "_id": ObjectId(), "precio": 9.9, "imagenProducto": "imagen11.jpg", "tipoProducto": "Diseno", "descripcion": "Imagen de una bicicleta", "CategoriaArticulo": "Camiseta", "seccionArticulo": "Hombre", "cantidadArticulo": 100}
);

// Insert Paquete in the Productos collection
db.Productos.insertOne(
    {"_id": ObjectId(), "precio": 9.9, "imagenProducto": "imagen12.jpg", "tipoProducto": "Paquete", "descripcion": "Combo de Yoga", "CategoriaArticulo": "Camiseta", "seccionArticulo": "Hombre", "cantidadArticulo": 100, "productosPaquete": [{"producto_id":db.Productos.findOne({ "descripcion": "Pantaloneta de yoga" })._id},{"producto_id":db.Productos.findOne({ "descripcion": "Zapatillas de yoga" })._id}]}
    );



// Insert data in the Compras collection
db.Compras.insertMany([
    { "_id": ObjectId(), "cuenta_usuario": "juanperez",      "costoTotal": 0.1, "fechaCompra": new Date("2024-01-17"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id,   "cantidad": 2, "talla": "M",     "personalizacion": {} }, { "producto_id": db.Productos.findOne({ "descripcion": "Polo deportiva" })._id,   "cantidad": 2, "talla": "M",     "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "analopez",       "costoTotal": 0.1, "fechaCompra": new Date("2024-02-13"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta deportiva" })._id, "cantidad": 1, "talla": "M",     "personalizacion": {} }, { "producto_id": db.Productos.findOne({ "descripcion": "Gorra de béisbol" })._id, "cantidad": 1, "talla": "Única", "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "carlosgarcia",   "costoTotal": 0.1, "fechaCompra": new Date("2024-03-13"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Gorra de béisbol" })._id,      "cantidad": 1, "talla": "Única", "personalizacion": {} }, { "producto_id": db.Productos.findOne({ "descripcion": "Polo deportiva" })._id,   "cantidad": 2, "talla": "M",     "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "luisamartinez",  "costoTotal": 0.1, "fechaCompra": new Date("2024-04-11"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Zapatillas deportivas" })._id, "cantidad": 2, "talla": "42",    "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "pedrorodriguez", "costoTotal": 0.1, "fechaCompra": new Date("2024-05-13"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Calcetines de algodón" })._id, "cantidad": 1, "talla": "M",     "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "martadiaz",      "costoTotal": 0.1, "fechaCompra": new Date("2024-01-13"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Polo deportiva" })._id,        "cantidad": 2, "talla": "M",     "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "jorgejimenez",   "costoTotal": 0.1, "fechaCompra": new Date("2024-02-13"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Gorra deportiva" })._id,       "cantidad": 1, "talla": "Única", "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "elenatorres",    "costoTotal": 0.1, "fechaCompra": new Date("2024-03-17"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Zapatillas de yoga" })._id,    "cantidad": 1, "talla": "Única", "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "miguelrojas",    "costoTotal": 0.1, "fechaCompra": new Date("2024-04-11"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta de yoga" })._id,   "cantidad": 2, "talla": "S",     "personalizacion": {} }] },
    { "_id": ObjectId(), "cuenta_usuario": "lauramendoza",   "costoTotal": 0.1, "fechaCompra": new Date("2024-05-17"), "detallesCompra": [{ "producto_id": db.Productos.findOne({ "descripcion": "Camiseta con capucha" })._id,  "cantidad": 2, "talla": "L",     "personalizacion": {} }] }
]);



// Insert data in the Envío collection
db.Envios.insertMany([
    { "_id": ObjectId(), "direccion": "Calle Principal 123",    "fechaEnvio": new Date("2024-01-17"), "codigoPostal": 12345, "compra_id": db.Compras.findOne({ "cuenta_usuario": "juanperez" })._id,      "lugar_id": db.Lugares.findOne({ "nombre": "Santiago de Cali" })._id },
    { "_id": ObjectId(), "direccion": "Avenida Secundaria 456", "fechaEnvio": new Date("2024-02-13"), "codigoPostal": 67890, "compra_id": db.Compras.findOne({ "cuenta_usuario": "analopez" })._id,       "lugar_id": db.Lugares.findOne({ "nombre": "Bogotá" })._id },
    { "_id": ObjectId(), "direccion": "Carrera 10 #45-23",      "fechaEnvio": new Date("2024-03-13"), "codigoPostal": 11223, "compra_id": db.Compras.findOne({ "cuenta_usuario": "carlosgarcia" })._id,   "lugar_id": db.Lugares.findOne({ "nombre": "Medellín" })._id },
    { "_id": ObjectId(), "direccion": "Calle 50 #20-10",        "fechaEnvio": new Date("2024-04-11"), "codigoPostal": 44556, "compra_id": db.Compras.findOne({ "cuenta_usuario": "luisamartinez" })._id,  "lugar_id": db.Lugares.findOne({ "nombre": "Barranquilla" })._id },
    { "_id": ObjectId(), "direccion": "Calle 30 #10-5",         "fechaEnvio": new Date("2024-05-13"), "codigoPostal": 33445, "compra_id": db.Compras.findOne({ "cuenta_usuario": "pedrorodriguez" })._id, "lugar_id": db.Lugares.findOne({ "nombre": "Cartagena" })._id },
    { "_id": ObjectId(), "direccion": "Carrera 15 #25-30",      "fechaEnvio": new Date("2024-01-13"), "codigoPostal": 66778, "compra_id": db.Compras.findOne({ "cuenta_usuario": "martadiaz" })._id,      "lugar_id": db.Lugares.findOne({ "nombre": "Bucaramanga" })._id },
    { "_id": ObjectId(), "direccion": "Avenida 5 #6-12",        "fechaEnvio": new Date("2024-02-13"), "codigoPostal": 99887, "compra_id": db.Compras.findOne({ "cuenta_usuario": "jorgejimenez" })._id,   "lugar_id": db.Lugares.findOne({ "nombre": "Pereira" })._id },
    { "_id": ObjectId(), "direccion": "Calle 70 #80-90",        "fechaEnvio": new Date("2024-03-17"), "codigoPostal": 55443, "compra_id": db.Compras.findOne({ "cuenta_usuario": "elenatorres" })._id,    "lugar_id": db.Lugares.findOne({ "nombre": "Manizales" })._id },
    { "_id": ObjectId(), "direccion": "Carrera 25 #35-40",      "fechaEnvio": new Date("2024-04-11"), "codigoPostal": 66789, "compra_id": db.Compras.findOne({ "cuenta_usuario": "miguelrojas" })._id,    "lugar_id": db.Lugares.findOne({ "nombre": "Santiago de Cali" })._id },
    { "_id": ObjectId(), "direccion": "Avenida 8 #12-15",       "fechaEnvio": new Date("2024-05-17"), "codigoPostal": 22334, "compra_id": db.Compras.findOne({ "cuenta_usuario": "lauramendoza" })._id,   "lugar_id": db.Lugares.findOne({ "nombre": "Santa Marta" })._id }
]);



// Insert data in the Reseñas collection
db.Reseñas.insertMany([
    { "_id": ObjectId(), "comentario": "¡Excelente producto!",   "valoracion": 5, "cuenta_usuario": "juanperez",      "fecha": new Date("2024-05-10"), "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id},
    { "_id": ObjectId(), "comentario": "Muy buena calidad",      "valoracion": 4, "cuenta_usuario": "analopez",       "fecha": new Date("2024-05-11"), "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta deportiva" })._id},
    { "_id": ObjectId(), "comentario": "Muy cómodo",             "valoracion": 4, "cuenta_usuario": "carlosgarcia",   "fecha": new Date("2024-05-12"), "producto_id": db.Productos.findOne({ "descripcion": "Gorra de béisbol" })._id},
    { "_id": ObjectId(), "comentario": "Perfecto para entrenar", "valoracion": 5, "cuenta_usuario": "luisamartinez",  "fecha": new Date("2024-05-13"), "producto_id": db.Productos.findOne({ "descripcion": "Zapatillas deportivas" })._id},
    { "_id": ObjectId(), "comentario": "Muy suaves",             "valoracion": 4, "cuenta_usuario": "pedrorodriguez", "fecha": new Date("2024-05-14"), "producto_id": db.Productos.findOne({ "descripcion": "Calcetines de algodón" })._id},
    { "_id": ObjectId(), "comentario": "Abriga bien",            "valoracion": 5, "cuenta_usuario": "martadiaz",      "fecha": new Date("2024-05-15"), "producto_id": db.Productos.findOne({ "descripcion": "Polo deportiva" })._id},
    { "_id": ObjectId(), "comentario": "Gran capacidad",         "valoracion": 5, "cuenta_usuario": "jorgejimenez",   "fecha": new Date("2024-05-16"), "producto_id": db.Productos.findOne({ "descripcion": "Gorra deportiva" })._id},
    { "_id": ObjectId(), "comentario": "Versátil",               "valoracion": 4, "cuenta_usuario": "elenatorres",    "fecha": new Date("2024-05-17"), "producto_id": db.Productos.findOne({ "descripcion": "Zapatillas de yoga" })._id},
    { "_id": ObjectId(), "comentario": "Muy flexibles",          "valoracion": 5, "cuenta_usuario": "miguelrojas",    "fecha": new Date("2024-05-18"), "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta de yoga" })._id},
    { "_id": ObjectId(), "comentario": "Me encanta el diseño",   "valoracion": 5, "cuenta_usuario": "lauramendoza",   "fecha": new Date("2024-05-19"), "producto_id": db.Productos.findOne({ "descripcion": "Camiseta con capucha" })._id}
]);



// Insert data in the Promociones collection
db.Promociones.insertMany([
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id} ],   "descuento": 0.10 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta deportiva" })._id} ], "descuento": 0.15 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Gorra de béisbol" })._id} ],      "descuento": 0.05 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Zapatillas deportivas" })._id} ], "descuento": 0.20 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Calcetines de algodón" })._id} ], "descuento": 0.10 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Polo deportiva" })._id} ],        "descuento": 0.25 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Gorra deportiva" })._id} ],       "descuento": 0.30 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Zapatillas de yoga" })._id} ],    "descuento": 0.15 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta de yoga" })._id} ],   "descuento": 0.20 },
    { "_id": ObjectId(), "fechaInicio": new Date("2024-05-01"), "fechaFin": new Date("2024-05-31"), "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Camiseta con capucha" })._id} ],  "descuento": 0.10 }
]);
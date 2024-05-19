// This script is used to insert data into the database
// But it has only one record for testing

// Insert data in the Lugares collection
db.Lugares.insertMany([
    { "_id": ObjectId(), "nombre": "Santiago de Cali", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Bogotá", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Medellín", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Barranquilla", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Cartagena", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Bucaramanga", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Pereira", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Manizales", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Cúcuta", "subLugar_id": ObjectId() },
    { "_id": ObjectId(), "nombre": "Santa Marta", "subLugar_id": ObjectId() }
]);

// Insert data in the Personas collection
db.Personas.insertMany([
    {
        "_id": ObjectId(), "nombre": "Juan Pérez", "correo": "juan@example.com", "telefono": "123456789",
        "genero": "Hombre", "edad": 30, "lugar_id": db.Lugares.findOne({ "nombre": "Santiago de Cali" })._id,
        "cuentas": [{ "usuario": "juanperez", "contraseña": "contraseña123", "tipoCuenta": "Cliente" }]
    },
    {
        "_id": ObjectId(), "nombre": "Ana López", "correo": "ana@example.com", "telefono": "987654321",
        "genero": "Mujer", "edad": 25, "lugar_id": db.Lugares.findOne({ "nombre": "Bogotá" })._id,
        "cuentas": [{ "usuario": "analopez", "contraseña": "contraseña123", "tipoCuenta": "Cliente" }]
    },
    {
        "_id": ObjectId(), "nombre": "Carlos García", "correo": "carlos@example.com", "telefono": "123123123",
        "genero": "Hombre", "edad": 35, "lugar_id": db.Lugares.findOne({ "nombre": "Medellín" })._id,
        "cuentas": [{ "usuario": "carlosgarcia", "contraseña": "contraseña123", "tipoCuenta": "Cliente" }]
    },
]);

// Insert data in the Productos collection
db.Productos.insertMany([
    {
        "_id": ObjectId(), "precio": 39.99, "imagenProducto": "imagen1.jpg", "tipoProducto": "Articulo",
        "descripcion": "Camiseta de algodón", "CategoriaArticulo": "Camiseta", "seccionArticulo": "Hombre",
        "cantidadArticulo": 100, "especificacionesArticulo": [{ "cantidad": 50, "talla": "M" }, { "cantidad": 50, "talla": "L" }]
    },
    {
        "_id": ObjectId(), "precio": 59.99, "imagenProducto": "imagen2.jpg", "tipoProducto": "Articulo",
        "descripcion": "Pantaloneta deportiva", "CategoriaArticulo": "Pantaloneta", "seccionArticulo": "Hombre",
        "cantidadArticulo": 80, "especificacionesArticulo": [{ "cantidad": 40, "talla": "M" }, { "cantidad": 40, "talla": "L" }]
    },
]);

// Insert data in the Compras collection
db.Compras.insertMany([
    {
        "_id": ObjectId(), "cuenta_usuario": "juanperez", "costoTotal": 79.98, "fechaCompra": new Date(),
        "detallesCompra": [{
            "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id,
            "cantidad": 2, "talla": "M", "personalizacion": {}
        }]
    },
    {
        "_id": ObjectId(), "cuenta_usuario": "analopez", "costoTotal": 59.99, "fechaCompra": new Date(),
        "detallesCompra": [{
            "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta deportiva" })._id,
            "cantidad": 1, "talla": "M", "personalizacion": {}
        }]
    },
]);

// Insert data in the Envío collection
db.Envios.insertMany([
    {
        "_id": ObjectId(), "direccion": "Calle Principal 123", "fechaEnvio": new Date(),
        "codigoPostal": 12345, "compra_id": db.Compras.findOne({ "cuenta_usuario": "juanperez" })._id,
        "lugar_id": db.Lugares.findOne({ "nombre": "Santiago de Cali" })._id
    },
    {
        "_id": ObjectId(), "direccion": "Avenida Secundaria 456", "fechaEnvio": new Date(),
        "codigoPostal": 67890, "compra_id": db.Compras.findOne({ "cuenta_usuario": "analopez" })._id,
        "lugar_id": db.Lugares.findOne({ "nombre": "Bogotá" })._id
    },
]);

// Insert data in the Reseñas collection
db.Reseñas.insertMany([
    {
        "_id": ObjectId(), "comentario": "¡Excelente producto!", "valoracion": 5,
        "cuenta_usuario": "juanperez", "fecha": new Date(),
        "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id
    },
    {
        "_id": ObjectId(), "comentario": "Muy buena calidad", "valoracion": 4,
        "cuenta_usuario": "analopez", "fecha": new Date(),
        "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta deportiva" })._id
    },
]);


// Insert data in the Promociones collection
db.Promociones.insertMany([
    {
        "_id": ObjectId(), "fechaInicio": new Date(),
        "fechaFin": new Date(Date.now() + (30 * 24 * 60 * 60 * 1000)), // 30 días después
        "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Camiseta de algodón" })._id }],
        "descuento": 0.10 // 10% de descuento
    },
    {
        "_id": ObjectId(), "fechaInicio": new Date(),
        "fechaFin": new Date(Date.now() + (30 * 24 * 60 * 60 * 1000)), // 30 días después
        "productos": [{ "producto_id": db.Productos.findOne({ "descripcion": "Pantaloneta deportiva" })._id }],
        "descuento": 0.15 // 15% de descuento
    },
]);
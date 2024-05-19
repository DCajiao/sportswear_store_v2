use sportswear_store;

db.createCollection("Personas", {
    "capped": false,
    "validator": {
        "$jsonSchema": {
            "bsonType": "object",
            "title": "Personas",
            "properties": {
                "_id": {
                    "bsonType": "objectId"
                },
                "nombre": {
                    "bsonType": "string"
                },
                "correo": {
                    "bsonType": "string"
                },
                "telefono": {
                    "bsonType": "string"
                },
                "genero": {
                    "bsonType": "string",
                    "enum": [
                        "Hombre",
                        "Mujer"
                    ]
                },
                "edad": {
                    "bsonType": "number"
                },
                "lugar_id": {
                    "bsonType": "objectId"
                },
                "cuentas": {
                    "bsonType": "array",
                    "additionalItems": true,
                    "items": {
                        "bsonType": "object",
                        "additionalProperties": false,
                        "patternProperties": {
                            "contraseña": {
                                "bsonType": "string"
                            },
                            "tipoCuenta": {
                                "bsonType": "string",
                                "enum": [
                                    "Administrador",
                                    "Cliente"
                                ]
                            },
                            "usuario": {
                                "bsonType": "string"
                            }
                        }
                    }
                }
            },
            "additionalProperties": true,
            "required": [
                "_id",
                "nombre",
                "correo"
            ]
        }
    },
    "validationLevel": "moderate",
    "validationAction": "error"
});




db.createCollection("Compras", {
    "capped": false,
    "validator": {
        "$jsonSchema": {
            "bsonType": "object",
            "title": "Compras",
            "properties": {
                "_id": {
                    "bsonType": "objectId"
                },
                "cuenta_usuario": {
                    "bsonType": "string"
                },
                "costoTotal": {
                    "bsonType": "double"
                },
                "fechaCompra": {
                    "bsonType": "date"
                },
                "detallesCompra": {
                    "bsonType": "array",
                    "additionalItems": true,
                    "items": {
                        "bsonType": "object",
                        "properties": {
                            "producto_id": {
                                "bsonType": "objectId"
                            },
                            "personalizacion": {
                                "bsonType": "object",
                                "additionalProperties": false,
                                "patternProperties": {
                                    "descripcion ": {
                                        "bsonType": "string"
                                    },
                                    "diseno_id": {
                                        "bsonType": "objectId"
                                    }
                                }
                            }
                        },
                        "additionalProperties": false,
                        "patternProperties": {
                            "cantidad": {
                                "bsonType": "number"
                            },
                            "talla": {
                                "bsonType": "string"
                            }
                        }
                    }
                }
            },
            "additionalProperties": true,
            "required": [
                "_id",
                "costoTotal",
                "fechaCompra"
            ]
        }
    },
    "validationLevel": "moderate",
    "validationAction": "error"
});




db.createCollection("Productos", {
    "capped": false,
    "validator": {
        "$jsonSchema": {
            "bsonType": "object",
            "title": "Productos",
            "properties": {
                "_id": {
                    "bsonType": "objectId"
                },
                "precio": {
                    "bsonType": "double"
                },
                "imagenProducto": {
                    "bsonType": "string"
                },
                "tipoProducto": {
                    "bsonType": "string",
                    "enum": [
                        "Articulo",
                        "Diseno",
                        "Paquete"
                    ]
                },
                "descripcion": {
                    "bsonType": "string"
                },
                "CategoriaArticulo": {
                    "bsonType": "string",
                    "enum": [
                        "Calcetines",
                        "Polo",
                        "Zapatillas",
                        "Gorra",
                        "Pantaloneta",
                        "Camiseta"
                    ]
                },
                "seccionArticulo": {
                    "bsonType": "string",
                    "enum": [
                        "Mujer",
                        "Hombre",
                        "Nino"
                    ]
                },
                "cantidadArticulo": {
                    "bsonType": "number"
                },
                "especificacionesArticulo": {
                    "bsonType": "array",
                    "additionalItems": true,
                    "items": {
                        "bsonType": "object",
                        "additionalProperties": false,
                        "patternProperties": {
                            "cantidad": {
                                "bsonType": "number"
                            },
                            "talla": {
                                "bsonType": "string"
                            }
                        }
                    }
                },
                "productosPaquete": {
                    "bsonType": "array",
                    "additionalItems": true,
                    "items": {
                        "bsonType": "object",
                        "additionalProperties": false,
                        "patternProperties": {
                            "producto_id": {
                                "bsonType": "objectId"
                            }
                        }
                    }
                }
            },
            "additionalProperties": true,
            "required": [
                "_id",
                "precio",
                "imagenProducto",
                "tipoProducto",
                "descripcion"
            ]
        }
    },
    "validationLevel": "moderate",
    "validationAction": "error"
});




db.createCollection("Envios", {
    "capped": false,
    "validator": {
        "$jsonSchema": {
            "bsonType": "object",
            "title": "Envios",
            "properties": {
                "_id": {
                    "bsonType": "objectId"
                },
                "direccion": {
                    "bsonType": "string"
                },
                "fechaEnvio": {
                    "bsonType": "date"
                },
                "codigoPostal": {
                    "bsonType": "number"
                },
                "compra_id": {
                    "bsonType": "objectId"
                },
                "lugar_id": {
                    "bsonType": "objectId"
                }
            },
            "additionalProperties": true,
            "required": [
                "_id",
                "direccion",
                "fechaEnvio",
                "codigoPostal"
            ]
        }
    },
    "validationLevel": "moderate",
    "validationAction": "error"
});




db.createCollection("Lugares", {
    "capped": false,
    "validator": {
        "$jsonSchema": {
            "bsonType": "object",
            "title": "Lugares",
            "properties": {
                "_id": {
                    "bsonType": "objectId"
                },
                "nombre": {
                    "bsonType": "string"
                },
                "subLugar_id": {
                    "bsonType": "objectId"
                }
            },
            "additionalProperties": true,
            "required": [
                "nombre"
            ]
        }
    },
    "validationLevel": "moderate",
    "validationAction": "error"
});




db.createCollection("Reseñas", {
    "capped": false,
    "validator": {
        "$jsonSchema": {
            "bsonType": "object",
            "title": "Reseñas",
            "properties": {
                "_id": {
                    "bsonType": "objectId"
                },
                "comentario": {
                    "bsonType": "string"
                },
                "valoracion": {
                    "bsonType": "number"
                },
                "cuenta_usuario": {
                    "bsonType": "string"
                },
                "fecha": {
                    "bsonType": "date"
                },
                "producto_id": {
                    "bsonType": "objectId"
                }
            },
            "additionalProperties": true,
            "patternProperties": {
                "replicas": {
                    "bsonType": "array",
                    "additionalItems": true,
                    "items": {
                        "bsonType": "object",
                        "additionalProperties": true,
                        "patternProperties": {
                            "comentario": {
                                "bsonType": "string"
                            },
                            "cuenta_usuario": {
                                "bsonType": "string"
                            },
                            "fecha": {
                                "bsonType": "date"
                            }
                        }
                    }
                }
            },
            "required": [
                "_id"
            ]
        }
    },
    "validationLevel": "moderate",
    "validationAction": "error"
});




db.createCollection("Promociones", {
    "capped": false,
    "validator": {
        "$jsonSchema": {
            "bsonType": "object",
            "title": "Promociones",
            "properties": {
                "_id": {
                    "bsonType": "objectId"
                },
                "fechaInicio": {
                    "bsonType": "date"
                },
                "fechaFin": {
                    "bsonType": "date"
                },
                "productos": {
                    "bsonType": "array",
                    "additionalItems": true,
                    "items": {
                        "bsonType": "object",
                        "additionalProperties": false,
                        "patternProperties": {
                            "producto_id": {
                                "bsonType": "objectId"
                            }
                        }
                    }
                },
                "descuento": {
                    "bsonType": "double"
                }
            },
            "additionalProperties": false,
            "required": [
                "fechaInicio",
                "fechaFin",
                "productos",
                "descuento"
            ]
        }
    },
    "validationLevel": "moderate",
    "validationAction": "error"
});

// Index creation

db.Personas.createIndex({ "correo": 1 }, { unique: true })

db.Personas.createIndex({ "telefono": 1 }, { unique: true })

db.Personas.createIndex({ "cuentas.usuario": 1 }, { unique: true })
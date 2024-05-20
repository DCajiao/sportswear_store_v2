from flask import Flask, jsonify, send_file

import mongoAtlas_client as mongoAtlas

#----------------------------------------------------------------
app = Flask(__name__)
#----------------------------------------------------------------

# 1. What is the top 10 places that have received the most shipments?
@app.route('/top_10_places')
def top_10_places():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = [
        {
            "$group": {
                "_id": "$lugar_id",
                "totalEnvios": { "$sum": 1 }
            }
        },
        {
            "$lookup": {
                "from": "Lugares",
                "localField": "_id",
                "foreignField": "_id",
                "as": "lugar"
            }
        },
        {
            "$unwind": "$lugar"
        },
        {
            "$sort": { "totalEnvios": -1 }
        },
        {
            "$limit": 10
        },
        {
            "$project": {
                "_id": 0,
                "lugar": "$lugar.nombre",
                "totalEnvios": 1
            }
        }
    ]
    # Execute aggregation query
    df = mongoAtlas.aggregate_query_to_atlas(db, "Envios", query)
    return jsonify(df.to_dict(orient="records"))

# 2. What are the 3 dates on which the most purchases have been made?
@app.route('/top_purchases_by_date')
def top_3_purchases_by_date():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = [
        {
            "$group": {
                "_id": { "$dateToString": { "format": "%Y-%m-%d", "date": "$fechaCompra" } },
                "totalCompras": { "$sum": 1 }
            }
        },
        {
            "$sort": { "totalCompras": -1 }
        },
        {
            "$limit": 3
        },
        {
            "$project": {
                "_id": 0,
                "fecha": "$_id",
                "totalCompras": 1
            }
        }
    ]
    df = mongoAtlas.aggregate_query_to_atlas(db, "Compras", query)
    return jsonify(df.to_dict(orient="records"))

# 3. How many sales have been made for each section?
@app.route('/sales_by_section')
def sales_by_section():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = [
        { "$unwind": "$detallesCompra" },
        {
            "$lookup": {
                "from": "Productos",
                "localField": "detallesCompra.producto_id",
                "foreignField": "_id",
                "as": "producto"
            }
        },
        { "$unwind": "$producto" },
        {
            "$group": {
                "_id": "$producto.seccionArticulo",
                "totalVentas": { "$sum": 1 }
            }
        },
        { "$sort": { "totalVentas": -1 } },
        { "$limit": 3 },
        {
            "$project": {
                "_id": 0,
                "seccion": "$_id",
                "totalVentas": 1
            }
        }
    ]
    df = mongoAtlas.aggregate_query_to_atlas(db, "Compras", query)
    return jsonify(df.to_dict(orient="records"))

# 4. How many item type products and how many design type products does the store have?
@app.route('/products_by_type')
def products_by_type():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = [
        {
            "$group": {
                "_id": "$tipoProducto",
                "cantidad": { "$sum": 1 }
            }
        },
        {
            "$match": {
                "_id": { "$in": ["Articulo", "Diseno"] }
            }
        }
    ]
    df = mongoAtlas.aggregate_query_to_atlas(db, "Productos", query)
    return jsonify(df.to_dict(orient="records"))

# 5. What are the 3 most expensive products?
@app.route('/top_3_expensive_products')
def top_3_expensive_products():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = { "tipoProducto": "Articulo" }
    projection = { "_id": 0, "descripcion": 1, "precio": 1 }
    sort = [("precio", -1)]
    limit = 3
    df = mongoAtlas.find_query_to_atlas(db, "Productos", query, projection, sort, limit)
    return jsonify(df.to_dict(orient="records"))

# 6. What is the total amount of sales per month for each year?
@app.route('/sales_by_month')
def sales_by_month():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = [
        {
            "$group": {
                "_id": {
                    "year": { "$year": "$fechaCompra" },
                    "month": { "$month": "$fechaCompra" }
                },
                "totalgeneradoVentas": { "$sum": "$costoTotal" }
            }
        },
        {
            "$sort": { "_id.year": 1, "_id.month": 1 }
        },
        {
            "$project": {
                "_id": 0,
                "year": "$_id.year",
                "month": "$_id.month",
                "totalVentas": "$totalgeneradoVentas"
            }
        }
    ]
    df = mongoAtlas.aggregate_query_to_atlas(db, "Compras", query)
    return jsonify(df.to_dict(orient="records"))

# 7. What is the average age of clients by gender?
@app.route('/average_client_age')
def average_client_age():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = [
        {
            "$group": {
                "_id": "$genero",
                "promedioEdad": { "$avg": "$edad" }
            }
        }
    ]
    df = mongoAtlas.aggregate_query_to_atlas(db, "Personas", query)
    return jsonify(df.to_dict(orient="records"))

# 8. Which 10 customers have made the most expensive purchases in terms of total cost?
@app.route('/top_10_expensive_clients')
def top_10_expensive_clients():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = [
        {
            "$sort": { "costoTotal": -1 }
        },
        {
            "$limit": 10
        },
        {
            "$lookup": {
                "from": "Personas",
                "localField": "cuenta_usuario",
                "foreignField": "cuentas.usuario",
                "as": "cliente"
            }
        },
        {
            "$unwind": "$cliente"
        },
        {
            "$project": {
                "_id": 0,
                "nombre": "$cliente.nombre",
                "correo": "$cliente.correo",
                "telefono": "$cliente.telefono",
                "costoTotal": 1
            }
        }
    ]
    df = mongoAtlas.aggregate_query_to_atlas(db, "Compras", query)
    return jsonify(df.to_dict(orient="records"))

# 9. What is the average cost of the items in each section (Hombre/Mujer/Nino)?
@app.route('/average_cost_by_section')
def average_cost_by_section():
    db = mongoAtlas.connect_to_mongodb_atlas()
    query = [
        {
            "$match": { "tipoProducto": "Articulo" }
        },
        {
            "$group": {
                "_id": "$seccionArticulo",
                "promedioCosto": { "$avg": "$precio" }
            }
        },
        {
            "$project": {
                "_id": 0,
                "seccion": "$_id",
                "promedioCosto": 1
            }
        }
    ]
    df = mongoAtlas.aggregate_query_to_atlas(db, "Productos", query)
    return jsonify(df.to_dict(orient="records"))


# ----------------------------------------------------------------
@app.route('/KEEPALIVE')
def mainpage():
    msg= "<center><h1>If you can see this message, it means that the API is still alive.</h1></center>"
    return msg
# ----------------------------------------------------------------

if __name__ == '__main__': 
    app.run(debug=True, host='0.0.0.0')
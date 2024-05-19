package com.apiweb.backend.Service;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.ProductosModel;

public interface IProductosService {
    ProductosModel buscarProductoPorId (ObjectId productoId);
}

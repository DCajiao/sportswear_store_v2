package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.ProductosModel;

public interface IProductosService {
    String guardarProducto(ProductosModel producto);
    ProductosModel buscarProductoPorId (ObjectId productoId);
    List<ProductosModel> listarProductos();
    String eliminarProductoPorId(ObjectId productoId);
    String actualizarProducto (ObjectId productoId, ProductosModel productoNuevo);
}

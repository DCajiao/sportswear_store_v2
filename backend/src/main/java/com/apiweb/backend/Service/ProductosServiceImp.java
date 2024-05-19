package com.apiweb.backend.Service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Repository.IProductosRepository;

@Service
public class ProductosServiceImp implements IProductosService{

    @Autowired IProductosRepository productosRepository;

    @Override
    public ProductosModel buscarProductoPorId(ObjectId productoId) {
        Optional <ProductosModel> productoRecuperado = productosRepository.findById(productoId);
        return productoRecuperado.orElseThrow(()-> new RecursoNoEncontradoException("¡ERROR! El producto con el ID "+productoId+" no se enncuentra en la BD"));
    }
    
}

package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Model.Documents.ProductosPaquete;
import com.apiweb.backend.Repository.IProductosRepository;

@Service
public class ProductosServiceImp implements IProductosService{

    @Autowired IProductosRepository productosRepository;

    @Override
    public String guardarProducto(ProductosModel producto) {
        for ( ProductosPaquete paquete: producto.getProductosPaquete()) {
            Optional<ProductosModel> productoId = productosRepository.findById(paquete.getProducto_id());
            if (productoId.isEmpty()) {
                return "¡ERROR! Uno o varios de los productos que se intentan registrar en el paquete no se encuentran en la BD";
            }
        }
        productosRepository.save(producto);
        return "El producto con el ID "+producto.getId()+" fue registrado con exito";
    }

    @Override
    public ProductosModel buscarProductoPorId(ObjectId productoId) {
        Optional <ProductosModel> productoRecuperado = productosRepository.findById(productoId);
        return productoRecuperado.orElseThrow(()-> new RecursoNoEncontradoException("¡ERROR! El producto con el ID "+productoId+" no se enncuentra en la BD"));
    }
    
    @Override
    public List<ProductosModel> listarProductos() {
        return productosRepository.findAll();
    }

    @Override
    public String eliminarProductoPorId(ObjectId productoId) {
        Optional<ProductosModel> productoRecuperado = productosRepository.findById(productoId);
        if (productoRecuperado.isPresent()) {
            productosRepository.delete(productoRecuperado.get());
            return "El producto con id "+productoId+" fue eliminado con exito";
        }else{
            throw new RecursoNoEncontradoException("El producto con el id "+productoId+" no fue encontrado en la BD");
        }
    }

    @Override
    public String actualizarProducto(ObjectId productoId, ProductosModel productoNuevo) {
        Optional<ProductosModel> productoEncontrado = productosRepository.findById(productoId);
        if (productoEncontrado.isPresent()) {
            ProductosModel producto = productoEncontrado.get();
            producto.setPrecio(productoNuevo.getPrecio());
            producto.setImagenProducto(productoNuevo.getImagenProducto());
            producto.setTipoProducto(productoNuevo.getTipoProducto());
            producto.setDescripcion(productoNuevo.getDescripcion());
            producto.setCategoriaArticulo(productoNuevo.getCategoriaArticulo());
            producto.setSeccionArticulo(productoNuevo.getSeccionArticulo());
            producto.setCantidadArticulo(productoNuevo.getCantidadArticulo());
            producto.setEspecificacionesArticulo(productoNuevo.getEspecificacionesArticulo());
            producto.setProductosPaquete(productoNuevo.getProductosPaquete());
            productosRepository.save(producto);
            return "El producto "+productoId+" fue actualizado con exito";
        }else{
            throw new RecursoNoEncontradoException("No se pudo encontrar el producto con el Id "+productoId);
        }
    }
}
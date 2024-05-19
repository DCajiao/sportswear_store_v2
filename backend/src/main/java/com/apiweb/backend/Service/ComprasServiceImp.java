package com.apiweb.backend.Service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.ComprasModel;
import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Model.Documents.DetallesCompra;
import com.apiweb.backend.Repository.IComprasRepository;
import com.apiweb.backend.Repository.IPersonasRepository;
import com.apiweb.backend.Repository.IProductosRepository;

@Service
public class ComprasServiceImp implements IComprasService{
    @Autowired IComprasRepository comprasRepository;
    @Autowired IProductosRepository productosRepository;
    @Autowired IPersonasRepository personasRepository;

    @Override
    public ComprasModel buscarCompraPorId(ObjectId compraId) {
        Optional <ComprasModel> compraRecuperada = comprasRepository.findById(compraId);
        return compraRecuperada.orElseThrow(()-> new RecursoNoEncontradoException("¡Error! La compra con el id "+compraId+" no se encuentra en la base de datos"));
    }

    @Override
    public String guardarCompra(ComprasModel compra) {
        for (DetallesCompra detalles : compra.getDetallesCompra()) {
            ObjectId productoId = detalles.getProducto_id();
            ProductosModel producto = productosRepository.findById(productoId).orElse(null);
            if (producto==null) {
                return "¡Error! El producto con el id "+productoId+" no existe en la base de datos";
            }
            detalles.setProducto_id(productoId);
        }
        comprasRepository.save(compra);
        return "La compra con el id"+compra.getId()+" fue creada con éxito";
    }

    @Override
    public String eliminarCompraPorId(ObjectId compraId) {
        Optional <ComprasModel> compraRecuperada = comprasRepository.findById(compraId);
        if (compraRecuperada.isPresent()) {
            comprasRepository.delete(compraRecuperada.get());
            return "La compra "+compraId+" fue eliminada con éxito";
        } else {
            throw new RecursoNoEncontradoException("La compra con el Id "+compraId+" no fue encontrada en la base de datos");
        }
    }
    
}

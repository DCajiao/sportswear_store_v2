package com.apiweb.backend.Service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.PersonasModel;
import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Model.ReseñasModel;
import com.apiweb.backend.Model.Documents.ReplicasReseñas;
import com.apiweb.backend.Repository.IPersonasRepository;
import com.apiweb.backend.Repository.IProductosRepository;
import com.apiweb.backend.Repository.IReseñasRepository;

@Service
public class ReseñasServiceImp implements IReseñaService{

    @Autowired IReseñasRepository reseñasRepository;
    @Autowired IPersonasRepository personasRepository;
    @Autowired IProductosRepository productosRepository;

    @Override
    public String guardarReseña(ReseñasModel reseña) {
        //Optional<PersonasModel> usuario = personasRepository.findByCuentasUsuario(reseña.getCuenta_usuario());
        //if (usuario.isEmpty()) {
        //    return "¡ERROR! El usuario "+reseña.getCuenta_usuario()+" no se encuentra en la BD";
        //}

        Optional<ProductosModel> producto = productosRepository.findById(reseña.getProducto_id());
        if (producto.isEmpty()) {
            return "¡ERROR! el producto con el ID "+reseña.getProducto_id()+" no se encuentra en la BD";
        }
        
        for (ReplicasReseñas replica : reseña.getReplicas()) {
            Optional<PersonasModel> usuarioReplica = personasRepository.findByCuentasUsuario(replica.getCuenta_Usuario());
            if (usuarioReplica.isEmpty()) {
                return "¡ERROR! El usuario que se intenta ingresar no se encuentra en la BD";
            }
        }
        reseñasRepository.save(reseña);
        return "La reseña del producto con id "+reseña.getProducto_id()+" hecha por el usuario "+reseña.getCuenta_usuario()+" fue creada con exito";
    }

    @Override
    public ReseñasModel buscarReseñaPorId(ObjectId reseñaId) {
        Optional <ReseñasModel> reseñaRecuperada = reseñasRepository.findById(reseñaId);
        return reseñaRecuperada.orElseThrow(()-> new RecursoNoEncontradoException("¡ERROR! La reseña con el ID "+reseñaId+" no se encuentra en la BD"));
    }

    @Override
    public String eliminarReseñaPorId(ObjectId reseñaId) {
        Optional<ReseñasModel> reseñaEncontrada = reseñasRepository.findById(reseñaId);
        if (reseñaEncontrada.isPresent()){
            reseñasRepository.delete(reseñaEncontrada.get());
            return "El producto"+reseñaId+" fue eliminado con exito";
        }else{
            throw new RecursoNoEncontradoException("Reseña no encontrada con el Id"+reseñaId);
        }
    }
    
}

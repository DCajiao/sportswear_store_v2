package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
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
       Optional<ObjectId> usuario = personasRepository.validarUsuario(reseña.getCuenta_usuario());
        if (usuario == null) {
            return "¡ERROR! El usuario "+reseña.getCuenta_usuario()+" no se encuentra en la BD";
        }

        Optional<ProductosModel> producto = productosRepository.findById(reseña.getProducto_id());
        if (producto.isEmpty()) {
            return "¡ERROR! el producto con el ID "+reseña.getProducto_id()+" no se encuentra en la BD";
        }
        
        for (ReplicasReseñas replica : reseña.getReplicas()) {
            Optional<ObjectId> usuarioReplica = personasRepository.validarUsuario(replica.getCuenta_Usuario());
            if (usuarioReplica == null) {
                return "¡ERROR! uno o varios de los usuarios que se intentan ingresar no se encuentran en la BD";
            }
        }
        reseñasRepository.save(reseña);
        return "La reseña con id "+reseña.getId()+" hecha por el usuario "+reseña.getCuenta_usuario()+" fue registrada con exito";
    }

    @Override
    public ReseñasModel buscarReseñaPorId(ObjectId reseñaId) {
        Optional <ReseñasModel> reseñaRecuperada = reseñasRepository.findById(reseñaId);
        return reseñaRecuperada.orElseThrow(()-> new RecursoNoEncontradoException("¡ERROR! La reseña con el ID "+reseñaId+" no se encuentra en la BD"));
    }
    
    @Override
    public List<ReseñasModel> listarReseñas() {
        return reseñasRepository.findAll();
    }

    @Override
    public List<ReseñasModel> ReseñasPorProducto(ObjectId productoId) {
        return reseñasRepository.ReseñasPorProducto(productoId);
    }

    @Override
    public String eliminarReseñaPorId(ObjectId reseñaId) {
        Optional <ReseñasModel> reseñaRecuperada = reseñasRepository.findById(reseñaId);
        if (reseñaRecuperada.isPresent()) {
            reseñasRepository.delete(reseñaRecuperada.get());
            return "La reseña "+reseñaId+" fue eliminada con éxito";
        }else{
            throw new RecursoNoEncontradoException("La reseña con el Id "+reseñaId+" no fue encontrada en la base de datos");}
        }

    @Override
    public String actualizarReseña(ObjectId reseñaId, ReseñasModel reseñaNueva) {
        Optional<ReseñasModel> reseñaEncontrada = reseñasRepository.findById(reseñaId);
        if (reseñaEncontrada.isPresent()){
            ReseñasModel reseña = reseñaEncontrada.get();
            reseña.setComentario(reseñaNueva.getComentario());
            reseña.setValoracion(reseñaNueva.getValoracion());
            reseña.setCuenta_usuario(reseñaNueva.getCuenta_usuario());
            reseña.setFecha(reseñaNueva.getFecha());
            reseña.setProducto_id(reseñaNueva.getProducto_id());
            reseña.setReplicas(reseñaNueva.getReplicas());
            reseñasRepository.save(reseña);
            return "La reseña "+reseñaId+" fue actualizada con exito";
        }else{
            throw new RecursoNoEncontradoException("No se puedo encontrar la reseña con el id "+reseñaId);
        }
    }
}
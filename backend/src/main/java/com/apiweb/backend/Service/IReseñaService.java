package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.ReseñasModel;

public interface IReseñaService {
    String guardarReseña(ReseñasModel reseña);
    ReseñasModel buscarReseñaPorId(ObjectId reseñaId);
    List<ReseñasModel> listarReseñas();
    List<ReseñasModel> ReseñasPorProducto (ObjectId productoId);
    String eliminarReseñaPorId(ObjectId reseñaId);
    String actualizarReseña (ObjectId reseñaId, ReseñasModel reseñaNueva);
}

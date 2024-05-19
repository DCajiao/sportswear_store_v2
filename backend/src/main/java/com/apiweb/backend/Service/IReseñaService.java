package com.apiweb.backend.Service;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.ReseñasModel;

public interface IReseñaService {
    String guardarReseña(ReseñasModel reseña);
    ReseñasModel buscarReseñaPorId(ObjectId reseñaId);
    String eliminarReseñaPorId(ObjectId reseñaId);
}

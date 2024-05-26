package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.EnviosModel;

public interface IEnviosService {
    EnviosModel buscarEnvioPorId(ObjectId envioId);
    String guardarEnvio(EnviosModel envio);
    String eliminarEnvioPorId(ObjectId envioId);
    String actualizarEnvio(ObjectId envioId, EnviosModel envioNuevo);
    List<EnviosModel> listarEnvios();
}

package com.apiweb.backend.Service;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.EnviosModel;

public interface IEnviosService {
    EnviosModel buscarEnvioPorId(ObjectId envioId);
}

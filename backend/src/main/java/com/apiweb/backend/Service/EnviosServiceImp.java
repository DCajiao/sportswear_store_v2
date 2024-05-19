package com.apiweb.backend.Service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.EnviosModel;
import com.apiweb.backend.Repository.IEnviosRepository;

@Service
public class EnviosServiceImp implements IEnviosService{
    @Autowired IEnviosRepository enviosRepository;

    @Override
    public EnviosModel buscarEnvioPorId(ObjectId envioId) {
        Optional <EnviosModel> envioRecuperado = enviosRepository.findById(envioId);
        return envioRecuperado.orElseThrow(()-> new RecursoNoEncontradoException("¡Error! El envio con el Id "+envioId+" no se encuentra en la base de datos"));
    }
    
}

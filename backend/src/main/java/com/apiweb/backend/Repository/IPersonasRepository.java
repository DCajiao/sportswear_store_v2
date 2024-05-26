package com.apiweb.backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.PersonasModel;


public interface IPersonasRepository extends MongoRepository <PersonasModel, ObjectId> {
    
    @Aggregation (pipeline = {
        "{ $match: { 'cuentas.usuario': ?0 }}",
        "{ $project: { _id: 1} }"
    })
    ObjectId validarUsuario (String usuario);
}

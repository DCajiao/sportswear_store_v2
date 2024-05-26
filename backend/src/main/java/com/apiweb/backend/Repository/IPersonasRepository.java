package com.apiweb.backend.Repository;


import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.apiweb.backend.Model.PersonasModel;


public interface IPersonasRepository extends MongoRepository <PersonasModel, ObjectId> {
    
    @Aggregation (pipeline = {
        "{ $unwind: { path: '$cuentas' }}",
        "{ $match: { 'cuentas.usuario': ?0 }}",
        "{ $project: { _id: 1} }"
    })
    Optional <ObjectId> validarUsuario (String usuario);
}

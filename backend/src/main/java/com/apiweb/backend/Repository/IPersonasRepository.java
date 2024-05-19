package com.apiweb.backend.Repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.PersonasModel;

public interface IPersonasRepository extends MongoRepository <PersonasModel, ObjectId> {
    Optional<PersonasModel> findByCuentasUsuario(String usuario);
}

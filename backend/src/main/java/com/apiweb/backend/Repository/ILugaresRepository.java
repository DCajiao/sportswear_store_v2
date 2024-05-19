package com.apiweb.backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.LugaresModel;

public interface ILugaresRepository extends MongoRepository<LugaresModel, ObjectId>{
    
}

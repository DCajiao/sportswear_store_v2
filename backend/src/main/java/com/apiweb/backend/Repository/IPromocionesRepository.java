package com.apiweb.backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.PromocionesModel;

public interface IPromocionesRepository extends MongoRepository <PromocionesModel, ObjectId> {
    
}

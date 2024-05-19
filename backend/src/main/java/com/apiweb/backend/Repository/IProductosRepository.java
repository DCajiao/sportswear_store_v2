package com.apiweb.backend.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.ProductosModel;

public interface IProductosRepository extends MongoRepository <ProductosModel, ObjectId> {
        
}

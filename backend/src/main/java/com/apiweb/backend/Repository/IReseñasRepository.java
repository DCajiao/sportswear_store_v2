package com.apiweb.backend.Repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.ReseñasModel;

public interface IReseñasRepository extends MongoRepository <ReseñasModel,ObjectId>{
    
    @Aggregation (pipeline =  {
        "{ $match: { producto_id: ?0}}",
        "{ $project: { _id: 0, producto_id: 0 } }"
    })
    List<ReseñasModel> ReseñasPorProducto (ObjectId productoId);
}

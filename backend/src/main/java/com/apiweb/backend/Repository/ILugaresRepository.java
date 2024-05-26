package com.apiweb.backend.Repository;


import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.LugaresModel;

public interface ILugaresRepository extends MongoRepository<LugaresModel, ObjectId>{
    
    @Aggregation (pipeline = {
        "{ $lookup: {from: 'Lugares', localField: 'subLugar_id', foreignField: '_id', as: 'subLugarInfo'}}",
        "{ $match: {_id: ?0}}",
        "{ $project: {_id:0, subLugar_id: 1, subLugarExiste: {$gt: [{ $size: '$subLugarInfo' }, 0]}}}"
    })
    Optional <String> validarSubLugar(ObjectId lugarId);
}
    //@Aggregation (pipeline = {
        //"{ $match: {subLugar_id: ?0}}",
        //"{ $project: { _id: 0, subLugar_id: 1 }}"
    //})
    //Optional <ObjectId> validarSubLugar(ObjectId subLugar);

     
  


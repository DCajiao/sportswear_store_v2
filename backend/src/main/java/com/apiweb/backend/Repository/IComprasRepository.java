package com.apiweb.backend.Repository;



import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.apiweb.backend.Model.ComprasModel;

public interface IComprasRepository extends MongoRepository<ComprasModel, ObjectId>{

    @Aggregation (pipeline = {
        "{ $unwind: { path: '$detallesCompra'}}",
        "{ $match: { _id: ?0}}",
        "{ $lookup: { from: 'Productos', localField: 'detallesCompra.producto_id', foreignField: '_id', as: 'productoInfo'}}",
        "{ $unwind: { path: '$productoInfo' } }",
        "{ $project: { _id: 0, tallaExiste: { $in: ['$detallesCompra.talla', '$productoInfo.especificacionesArticulo.talla']}}}"
    })
    List<Boolean> validarTalla(ObjectId compraId);
}


package com.apiweb.backend.Service;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.ComprasModel;

public interface IComprasService {
    ComprasModel buscarCompraPorId(ObjectId compraId);
    String guardarCompra(ComprasModel compra);
    String eliminarCompraPorId(ObjectId compraId);
}

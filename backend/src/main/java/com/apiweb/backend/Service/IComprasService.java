package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.ComprasModel;

public interface IComprasService {
    ComprasModel buscarCompraPorId(ObjectId compraId);
    String guardarCompra(ComprasModel compra);
    String eliminarCompraPorId(ObjectId compraId);
    String actualizarCompra (ObjectId compraId, ComprasModel compraNuevo);
    List<ComprasModel> listarCompras();
    List<Boolean> validarTalla(ObjectId compraId);
}

package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.PromocionesModel;

public interface IPromocionesService {
    String guardarPromocion(PromocionesModel promocion);
    PromocionesModel buscarPromocionPorId (ObjectId promocionId);
    List<PromocionesModel> listarPromociones();
    String eliminarPromocionPorId(ObjectId promocionId);
    String actualizarPromocion (ObjectId promocionId, PromocionesModel promoNueva);
}

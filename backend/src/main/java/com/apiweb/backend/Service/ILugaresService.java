package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.LugaresModel;

public interface ILugaresService {
    LugaresModel buscarLugaresPorId(ObjectId lugarId);
    String guardarLugar(LugaresModel subLugar);
    String eliminarLugar(ObjectId lugarId);
    String actualizarLugar(ObjectId lugarId, LugaresModel lugarNuevo);
    List<LugaresModel> listarLugares();
}

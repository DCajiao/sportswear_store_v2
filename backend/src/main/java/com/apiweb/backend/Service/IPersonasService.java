package com.apiweb.backend.Service;

import java.util.List;

import org.bson.types.ObjectId;

import com.apiweb.backend.Model.PersonasModel;

public interface IPersonasService {
    PersonasModel buscarPersonaPorId(ObjectId personaId);
    String guardarPersona(PersonasModel persona);
    String eliminarPersonaPorId(ObjectId personaId);
    String actualizarPersona(ObjectId personaId, PersonasModel personaNuevo);
    List<PersonasModel> listarPersonas();
}

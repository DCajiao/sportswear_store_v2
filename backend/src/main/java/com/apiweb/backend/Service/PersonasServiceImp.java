package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.LugaresModel;
import com.apiweb.backend.Model.PersonasModel;
import com.apiweb.backend.Repository.ILugaresRepository;
import com.apiweb.backend.Repository.IPersonasRepository;

@Service
public class PersonasServiceImp implements IPersonasService{
    @Autowired IPersonasRepository personasRepository;
    @Autowired ILugaresRepository lugaresRepository;

    @Override
    public PersonasModel buscarPersonaPorId(ObjectId personaId) {
        Optional <PersonasModel> personarecuperada = personasRepository.findById(personaId);
        return personarecuperada.orElseThrow(()-> new RecursoNoEncontradoException("¡ERROR! La persona que se desea buscar no existe en la base de datos"));
    }

    @Override
    public String guardarPersona(PersonasModel persona) {
        Optional <LugaresModel> lugar =lugaresRepository.findById(persona.getLugar_id());
        if (lugar.isEmpty()) {
            return "¡ERROR! El lugar que se desea ingresar no existe en la base de datos";
        }
        personasRepository.save(persona);
        return "La persona ingresada fue creado con exito";
    }

    @Override
    public String eliminarPersonaPorId(ObjectId personaId) {
        Optional <PersonasModel> personaRecuperada = personasRepository.findById(personaId);
        if (personaRecuperada.isPresent()) {
            personasRepository.delete(personaRecuperada.get());
            return "La persona ingresada fue eliminada con exito";
        } else {
            throw new RecursoNoEncontradoException("La persona que se desea eliminar no existe en la base de datos");
        }
    }

    @Override
    public String actualizarPersona(ObjectId personaId, PersonasModel personaNuevo) {
        Optional <PersonasModel> personaRecuperada = personasRepository.findById(personaId);
        if (personaRecuperada.isPresent()) {
            PersonasModel persona = personaRecuperada.get();
            persona.setNombre(personaNuevo.getNombre());
            persona.setCorreo(personaNuevo.getCorreo());
            persona.setTelefono(personaNuevo.getTelefono());
            persona.setEdad(personaNuevo.getEdad());
            persona.setLugar_id(personaNuevo.getLugar_id());
            persona.setCuentas(personaNuevo.getCuentas());
            personasRepository.save(persona);
            return "La persona ingresada fue actualizada con exito";
        } else {
            return "La persona que se intenta actualizar no existe en la base de datos";
        }
    }

    @Override
    public List<PersonasModel> listarPersonas() {
        return personasRepository.findAll();
    }
    
}

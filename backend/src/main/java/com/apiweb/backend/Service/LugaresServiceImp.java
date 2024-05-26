package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.LugaresModel;
import com.apiweb.backend.Repository.ILugaresRepository;

@Service
public class LugaresServiceImp implements ILugaresService{
    @Autowired ILugaresRepository lugaresRepository;

    @Override
    public LugaresModel buscarLugaresPorId(ObjectId lugarId) {
        Optional <LugaresModel> lugarRecuperado = lugaresRepository.findById(lugarId);
        return lugarRecuperado.orElseThrow(()-> new RecursoNoEncontradoException("¡ERROR! El lugar que se desea buscar no existe en la base de datos"));
    }

    @Override
    public String guardarLugar(LugaresModel lugar) {
        Optional<String> subLugar = lugaresRepository.validarSubLugar(lugar.getSubLugar_id());
        if (subLugar.isEmpty()) {
            return "¡ERROR! El SubLugar proporcionado no existe en la base de datos";
        }
        lugaresRepository.save(lugar);
        return "El lugar fue creado con éxito";
    }

    @Override
    public String eliminarLugar(ObjectId lugarId) {
        Optional <LugaresModel> lugarRecuperado = lugaresRepository.findById(lugarId);
        if (lugarRecuperado.isPresent()) {
            lugaresRepository.delete(lugarRecuperado.get());
            return "El lugar fue eliminado con exito";
        } else {
            throw new RecursoNoEncontradoException("¡ERROR! El lugar que se desea eliminar no se encuentra en la base de datos");
        }
    }

    @Override
    public String actualizarLugar(ObjectId lugarId, LugaresModel lugarNuevo) {
        Optional <LugaresModel> lugarRecuperado = lugaresRepository.findById(lugarId);
        if (lugarRecuperado.isPresent()) {
            LugaresModel lugar = lugarRecuperado.get();
            lugar.setNombre(lugarNuevo.getNombre());
            lugar.setSubLugar_id(lugarNuevo.getSubLugar_id());
            lugaresRepository.save(lugar);
            return "El lugar fue actualizado con exito";
        } else {
            return "El lugar que se desea actualizar no existe en la base de datos";
        }
    }

    @Override
    public List<LugaresModel> listarLugares() {
        return lugaresRepository.findAll();
    }
    
}

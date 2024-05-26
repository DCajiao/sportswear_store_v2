package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.ComprasModel;
import com.apiweb.backend.Model.EnviosModel;
import com.apiweb.backend.Model.LugaresModel;
import com.apiweb.backend.Repository.IComprasRepository;
import com.apiweb.backend.Repository.IEnviosRepository;
import com.apiweb.backend.Repository.ILugaresRepository;

@Service
public class EnviosServiceImp implements IEnviosService{
    @Autowired IEnviosRepository enviosRepository;
    @Autowired IComprasRepository comprasRepository;
    @Autowired ILugaresRepository lugaresRepository;

    @Override
    public EnviosModel buscarEnvioPorId(ObjectId envioId) {
        Optional <EnviosModel> envioRecuperado = enviosRepository.findById(envioId);
        return envioRecuperado.orElseThrow(()-> new RecursoNoEncontradoException("¡Error! El envio que se desea buscar no se encuentra en la base de datos"));
    }

    @Override
    public String guardarEnvio(EnviosModel envio) {
        Optional <ComprasModel> compra = comprasRepository.findById(envio.getCompra_id());
        if (compra.isEmpty()) {
            return "¡Error! La compra ingresada no esta en la base de datos";
        }
        Optional <LugaresModel> lugar = lugaresRepository.findById(envio.getLugar_id());
        if (lugar.isEmpty()) {
            return "¡Error! El lugar ingresado no esta en la base de datos";
        }
        enviosRepository.save(envio);
        return "El envio fue creado con exito";
    }

    @Override
    public String eliminarEnvioPorId(ObjectId envioId) {
        Optional <EnviosModel> envioRecuperado = enviosRepository.findById(envioId);
        if (envioRecuperado.isPresent()) {
            enviosRepository.delete(envioRecuperado.get());
            return ("El envio fue eliminado con éxito");
        }else{
            throw new RecursoNoEncontradoException("El envio que se desea eliminar no fue encontrada en la base de datos");
        }
    }

    @Override
    public String actualizarEnvio(ObjectId envioId, EnviosModel envioNuevo) {
        Optional<EnviosModel> envioRecuperado = enviosRepository.findById(envioId);
        if (envioRecuperado.isPresent()) {
            EnviosModel envio = envioRecuperado.get();
            envio.setDireccion(envioNuevo.getDireccion());
            envio.setCodigoPostal(envioNuevo.getCodigoPostal());
            envio.setLugar_id(envioNuevo.getLugar_id());
            enviosRepository.save(envio);
            return "El envío fue actualizado con éxito";
        } else {
            return "El envio que se desea actualizar no existe en la base de datos";
        }
    }

    @Override
    public List<EnviosModel> listarEnvios() {
        return enviosRepository.findAll();
    }
}

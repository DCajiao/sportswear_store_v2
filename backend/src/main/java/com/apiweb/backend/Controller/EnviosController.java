package com.apiweb.backend.Controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.EnviosModel;
import com.apiweb.backend.Service.IEnviosService;

@RestController
@RequestMapping ("/envios")
public class EnviosController {
    @Autowired IEnviosService enviosService;

    @GetMapping ("/buscarPorId/{id}")
    public ResponseEntity<?> buscarEnvioPorId(@PathVariable ObjectId id){
        try {
            EnviosModel envio = enviosService.buscarEnvioPorId(id);
            return ResponseEntity.ok(envio);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping ("/guardar")
    public ResponseEntity<String> crearEnvio(@RequestBody EnviosModel envio){
        return new ResponseEntity<String>(enviosService.guardarEnvio(envio), HttpStatus.OK);
    }

    @DeleteMapping ("/eliminarEnvio/{id}")
    public ResponseEntity<String> eliminarEnvioPorId(@PathVariable ObjectId id) {
        try {
            String mensaje = enviosService.eliminarEnvioPorId(id);
            return ResponseEntity.ok(mensaje);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping ("/actualizarEnvio/{id}")
    public ResponseEntity<?> actualizarEnvio(@PathVariable("id") ObjectId getId, @RequestBody EnviosModel envio) {
        Object envioActualizado = enviosService.actualizarEnvio(getId, envio);
        if (envioActualizado != null) {
            return ResponseEntity.ok().body(envioActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Envío no encontrado");
        }
    }

    @GetMapping ("/listarEnvios")
    public ResponseEntity<List<EnviosModel>> listarEnvios(){
        List<EnviosModel> envios = enviosService.listarEnvios();
        return new ResponseEntity<List<EnviosModel>>(envios, HttpStatus.OK);
    }
}

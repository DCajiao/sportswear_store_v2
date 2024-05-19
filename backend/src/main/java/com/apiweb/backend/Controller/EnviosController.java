package com.apiweb.backend.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

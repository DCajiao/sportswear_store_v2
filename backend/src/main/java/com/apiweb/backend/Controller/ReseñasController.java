package com.apiweb.backend.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.ReseñasModel;
import com.apiweb.backend.Service.IReseñaService;

@RestController
@RequestMapping("/reseñas")
public class ReseñasController {

    @Autowired IReseñaService reseñaService;

    @PostMapping("/crearReseñas")
    public ResponseEntity<String> crearReseña(@RequestBody ReseñasModel reseña) {
        return new ResponseEntity<String>(reseñaService.guardarReseña(reseña), HttpStatus.OK);
    }

    @GetMapping("/buscarporid/{id}")
    public ResponseEntity<?> buscarReseñaPorId(@PathVariable ObjectId id) {
        try {
            ReseñasModel reseña = reseñaService.buscarReseñaPorId(id);
            return ResponseEntity.ok(reseña);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminarReseña/{id}")
    public ResponseEntity<?> eliminarReseñaPorID(@PathVariable ObjectId id) {
        try {
            reseñaService.eliminarReseñaPorId(id);
            return ResponseEntity.ok().build();
        } catch (RecursoNoEncontradoException e){
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
}

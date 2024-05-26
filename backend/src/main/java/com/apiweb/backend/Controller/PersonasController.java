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
import com.apiweb.backend.Model.PersonasModel;
import com.apiweb.backend.Service.IPersonasService;

@RestController
@RequestMapping ("/personas")
public class PersonasController {
    @Autowired IPersonasService personasService;

    @GetMapping ("/buscarPorId/{id}")
    public ResponseEntity<?> buscarPersonaPorId(@PathVariable ObjectId id){
        try {
            PersonasModel persona = personasService.buscarPersonaPorId(id);
            return ResponseEntity.ok(persona);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping ("/guardar")
    public ResponseEntity<String> crearPersona(@RequestBody PersonasModel persona){
        return new ResponseEntity<String>(personasService.guardarPersona(persona), HttpStatus.OK);
    }

    @DeleteMapping ("/eliminarPersona/{id}")
    public ResponseEntity<String> eliminarPersonaPorId(@PathVariable ObjectId id){
        try {
            String mensaje = personasService.eliminarPersonaPorId(id);
            return ResponseEntity.ok(mensaje);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping ("/actualizarPersona/{id}")
    public ResponseEntity<?> actualizarPersona(@PathVariable("id") ObjectId getId, @RequestBody PersonasModel persona){
        Object personaActualizada = personasService.actualizarPersona(getId, persona);
        if (personaActualizada != null) {
            return ResponseEntity.ok().body(personaActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada");
        }
    }

    @GetMapping ("/listarPersonas")
    public ResponseEntity<List<PersonasModel>> listarPersonas(){
        List<PersonasModel> personas = personasService.listarPersonas();
        return new ResponseEntity<List<PersonasModel>>(personas, HttpStatus.OK);
    }
}

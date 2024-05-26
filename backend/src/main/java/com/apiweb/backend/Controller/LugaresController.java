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

import com.apiweb.backend.Model.LugaresModel;
import com.apiweb.backend.Service.ILugaresService;

@RestController
@RequestMapping ("/lugares")
public class LugaresController {
    @Autowired ILugaresService lugaresService;

    @GetMapping ("/buscarPorId/{id}")
    public ResponseEntity<?> buscarLugarPorId(@PathVariable ObjectId id){
        try {
            LugaresModel lugar = lugaresService.buscarLugaresPorId(id);
            return ResponseEntity.ok(lugar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping ("/guardar")
    public ResponseEntity<String> crearLugar(@RequestBody LugaresModel lugar){
        return new ResponseEntity<String>(lugaresService.guardarLugar(lugar),HttpStatus.OK);
    }

    @DeleteMapping ("/eliminarLugar/{id}")
    public ResponseEntity<?> eliminarLugarPorId(@PathVariable ObjectId id){
        try {
            String mensaje = lugaresService.eliminarLugar(id);
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping ("/actualizarLugar/{id}")
    public ResponseEntity<?> actualizarLugar(@PathVariable("id") ObjectId getId, @RequestBody LugaresModel lugar){
        Object lugarActualizado = lugaresService.actualizarLugar(getId, lugar);
        if (lugarActualizado != null) {
            return ResponseEntity.ok().body(lugarActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lugar no encontrado");
        }
    }

    @GetMapping ("/listarLugares")
    public ResponseEntity<List<LugaresModel>> listarLugares(){
        List<LugaresModel> lugares = lugaresService.listarLugares();
        return new ResponseEntity<List<LugaresModel>>(lugares, HttpStatus.OK);
    }
}

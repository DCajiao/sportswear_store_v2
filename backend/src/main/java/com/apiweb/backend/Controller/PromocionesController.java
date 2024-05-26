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
import com.apiweb.backend.Model.PromocionesModel;
import com.apiweb.backend.Service.IPromocionesService;

@RestController
@RequestMapping("/promociones")
public class PromocionesController {
    
    @Autowired IPromocionesService promocionesService;

    @PostMapping("/crearPromo")
    public ResponseEntity<String> crearPromo(@RequestBody PromocionesModel promocion){
        return new ResponseEntity<String>(promocionesService.guardarPromocion(promocion), HttpStatus.OK);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarPromoPorId(@PathVariable ObjectId id){
        try{
            PromocionesModel promo = promocionesService.buscarPromocionPorId(id);
            return ResponseEntity.ok(promo);
        }catch(Exception e){ 
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/listarPromociones")
    public ResponseEntity<List<PromocionesModel>> listarPromos(){
        List<PromocionesModel> promos = promocionesService.listarPromociones();
        return new ResponseEntity<List<PromocionesModel>>(promos, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarPromo/{id}")
    public ResponseEntity<?> eliminarPromoPorId(@PathVariable ObjectId id){
        try{
            String mensaje = promocionesService.eliminarPromocionPorId(id);
            return ResponseEntity.ok(mensaje);
        }catch(RecursoNoEncontradoException e){
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping("/actualizarPromo/{id}")
    public ResponseEntity<?> actualizarPromoPorId(@PathVariable("id") ObjectId Id, @RequestBody PromocionesModel promo){
        try{
            String mensaje = promocionesService.actualizarPromocion(Id, promo);
            return ResponseEntity.ok(mensaje);
        }catch(RecursoNoEncontradoException e){
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
}
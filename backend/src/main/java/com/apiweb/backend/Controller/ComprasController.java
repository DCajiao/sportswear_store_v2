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

import com.apiweb.backend.Model.ComprasModel;
import com.apiweb.backend.Service.IComprasService;

@RestController
@RequestMapping ("/compras")
public class ComprasController {
    @Autowired IComprasService comprasService;

    @GetMapping ("/buscarPorId/{id}")
    public ResponseEntity<?> buscarCompraPorId(@PathVariable ObjectId id){
        try {
            ComprasModel compra = comprasService.buscarCompraPorId(id);
            return ResponseEntity.ok(compra);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping ("/guardar")
    public ResponseEntity<String> crearCompra(@RequestBody ComprasModel compra){
        return new ResponseEntity<String>(comprasService.guardarCompra(compra), HttpStatus.OK);
    }

    @DeleteMapping ("/eliminarCompra/{id}")
    public ResponseEntity<?> eliminarCompraPorId(@PathVariable ObjectId id){
        try {
            comprasService.eliminarCompraPorId(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

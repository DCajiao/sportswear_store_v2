package com.apiweb.backend.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Service.IProductosService;

@RestController
@RequestMapping ("/productos")
public class ProductosController {
    @Autowired IProductosService productosService;

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable ObjectId id){
        try {
            ProductosModel producto = productosService.buscarProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

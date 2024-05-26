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
import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Service.IProductosService;

@RestController
@RequestMapping ("/productos")
public class ProductosController {

    @Autowired IProductosService productosService;

    @PostMapping("/crearProducto")
    public ResponseEntity<String> crearProducto(@RequestBody ProductosModel producto){
        return new ResponseEntity<String>(productosService.guardarProducto(producto), HttpStatus.OK);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable ObjectId id){
        try {
            ProductosModel producto = productosService.buscarProductoPorId(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/listarProductos")
    public ResponseEntity<List<ProductosModel>> listarProductos(){
        List<ProductosModel> productos = productosService.listarProductos();
        return new ResponseEntity<List<ProductosModel>>(productos, HttpStatus.OK);
    }

    @DeleteMapping("/eliminarProducto/{id}")
    public ResponseEntity<?> eliminarProductoPorId(@PathVariable ObjectId id){
        try {
            String mensaje = productosService.eliminarProductoPorId(id);
            return ResponseEntity.ok(mensaje);
        }catch(RecursoNoEncontradoException e){
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping("/actualizarProducto/{id}")
    public ResponseEntity<?> actualizarProductoPorId(@PathVariable("id") ObjectId Id, @RequestBody ProductosModel producto){
        try{
            String mensaje = productosService.actualizarProducto(Id, producto);
            return ResponseEntity.ok(mensaje);
        } catch(RecursoNoEncontradoException e){
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
}
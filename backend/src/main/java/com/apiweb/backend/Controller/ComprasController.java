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
            String mensaje = comprasService.eliminarCompraPorId(id);
            return ResponseEntity.ok(mensaje);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al eliminar la compra");
        }
    }

    @PutMapping ("/actualizarCompra/{id}")
    public ResponseEntity<?> actualizarCompra(@PathVariable("id") ObjectId getId, @RequestBody ComprasModel compra){
        Object compraActualizada = comprasService.actualizarCompra(getId, compra);
        if (compraActualizada != null) {
            return ResponseEntity.ok().body(compraActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra no encontrada");
        }
    }

    @GetMapping ("/listarCompras")
    public ResponseEntity<List<ComprasModel>> listarCompras(){
        List<ComprasModel> compras = comprasService.listarCompras();
        return new ResponseEntity<List<ComprasModel>>(compras, HttpStatus.OK);
    }

    @GetMapping("/validarTalla/{id}")
    public List<Boolean> validarTalla(@PathVariable ObjectId id) {
        List<Boolean> resultado = comprasService.validarTalla(id);
        System.out.println("Respuesta del controlador para compraId: " + id + " es: " + resultado);
        return resultado;
    }
}

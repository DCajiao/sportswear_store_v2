package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.ComprasModel;
import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Model.Documents.DetallesCompra;
import com.apiweb.backend.Repository.IComprasRepository;
import com.apiweb.backend.Repository.IPersonasRepository;
import com.apiweb.backend.Repository.IProductosRepository;

@Service
public class ComprasServiceImp implements IComprasService{
    @Autowired IComprasRepository comprasRepository;
    @Autowired IProductosRepository productosRepository;
    @Autowired IPersonasRepository personasRepository;

    @Override
    public ComprasModel buscarCompraPorId(ObjectId compraId) {
        Optional <ComprasModel> compraRecuperada = comprasRepository.findById(compraId);
        return compraRecuperada.orElseThrow(()-> new RecursoNoEncontradoException("¡Error! La compra que se desea buscar no se encuentra en la base de datos"));
    }

    @Override
    public String guardarCompra(ComprasModel compra) {
        Optional <ObjectId> usuario = personasRepository.validarUsuario(compra.getCuenta_usuario());
        if (usuario.isEmpty()) {
          return "¡Error! El usuario ingresado no se encuentra en la base de datos";
        }
        for (DetallesCompra detalles : compra.getDetallesCompra()) {
            ObjectId productoId = detalles.getProducto_id();
            Optional<ProductosModel> producto = productosRepository.findById(productoId);
            if (!producto.isPresent()) {
                return "¡Error! El producto ingresado no existe en la base de datos";
            }
            ObjectId compraId = compra.getId();
            List<Boolean> tallas = comprasRepository.validarTalla(compraId);
            for (Boolean talla : tallas){
                if (Boolean.FALSE.equals(talla)) {
                    return "¡ERROR! La talla ingresada no existe en la base de datos";
                }
            }
            // y la cantidad  y la talla?
            detalles.setProducto_id(productoId);
        }
        comprasRepository.save(compra);
        return "La compra fue creada con éxito";
    }

    @Override
    public String eliminarCompraPorId(ObjectId compraId) {
        Optional <ComprasModel> compraRecuperada = comprasRepository.findById(compraId);
        if (compraRecuperada.isPresent()) {
            comprasRepository.delete(compraRecuperada.get());
            return "La compra fue eliminada con éxito";
        }else{
            throw new RecursoNoEncontradoException("La compra que se desea eliminar no fue encontrada en la base de datos");
        }
    }

    @Override
    public String actualizarCompra(ObjectId compraId, ComprasModel compraNuevo) {
        Optional <ComprasModel> compraRecuperada = comprasRepository.findById(compraId);
        if (compraRecuperada.isPresent()) {
            ComprasModel compra = compraRecuperada.get();
            compra.setCostoTotal(compraNuevo.getCostoTotal());
            compra.setFechaCompra(compraNuevo.getFechaCompra());
            compra.setCuenta_usuario(compraNuevo.getCuenta_usuario());
            compra.setDetallesCompra(compraNuevo.getDetallesCompra());
            comprasRepository.save(compra);
        return ("La compra fue actualizada con exito");
        } else {
            return ("La compra que se desea actualizar no existe en la base de datos");
        }
    }

    @Override
    public List<ComprasModel> listarCompras() {
        return comprasRepository.findAll();
    }

    @Override
    public List<Boolean> validarTalla(ObjectId compraId) {
        System.out.println("Validando talla para compraId: " + compraId);
        List<Boolean> resultado = comprasRepository.validarTalla(compraId);
        System.out.println("Resultado de la validación: " + resultado);
        return resultado;
    }
}

package com.apiweb.backend.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apiweb.backend.Exception.RecursoNoEncontradoException;
import com.apiweb.backend.Model.ProductosModel;
import com.apiweb.backend.Model.PromocionesModel;
import com.apiweb.backend.Model.Documents.ProductosPromociones;
import com.apiweb.backend.Repository.IProductosRepository;
import com.apiweb.backend.Repository.IPromocionesRepository;

@Service
public class PromocionesServiceImp implements IPromocionesService{

    @Autowired IPromocionesRepository promocionesRepository;
    @Autowired IProductosRepository productosRepository;

    @Override
    public String guardarPromocion(PromocionesModel promocion) {
        for (ProductosPromociones productos: promocion.getProductos()) {
            Optional<ProductosModel> productoId = productosRepository.findById(productos.getProducto_id());
            if (productoId.isEmpty()) {
                return "¡ERROR! uno o varios de los productos que se intentan registrar en la promocion no se encuentran en la BD";
            }
        }
        promocionesRepository.save(promocion);
        return "La promocion con el ID "+promocion.getId()+" fue registrada con exito";
    
    }

    @Override
    public PromocionesModel buscarPromocionPorId(ObjectId promocionId) {
        Optional<PromocionesModel> promocionRecuperada = promocionesRepository.findById(promocionId);
        return promocionRecuperada.orElseThrow(()-> new RecursoNoEncontradoException("¡Error! La promocion con el Id "+promocionId+" no se encuentra en la BD"));
    }

    @Override
    public List<PromocionesModel> listarPromociones() {
        return promocionesRepository.findAll();
    }

    @Override
    public String eliminarPromocionPorId(ObjectId promocionId) {
        Optional<PromocionesModel> promocionRecuperada = promocionesRepository.findById(promocionId);
        if (promocionRecuperada.isPresent()){
            promocionesRepository.delete(promocionRecuperada.get());
            return "La promocion con el id "+promocionId+" fue eliminada con exito";
        }else{
            throw new RecursoNoEncontradoException("La promocion con el id "+promocionId+" no fue encontrada en la BD");
        }
    }

    @Override
    public String actualizarPromocion(ObjectId promocionId, PromocionesModel promoNueva) {
        Optional<PromocionesModel> promocionEncontrada = promocionesRepository.findById(promocionId);
        if (promocionEncontrada.isPresent()){
            PromocionesModel promocion = promocionEncontrada.get();
            promocion.setFechaInicio(promoNueva.getFechaInicio());
            promocion.setFechaFin(promoNueva.getFechaFin());
            promocion.setProductos(promoNueva.getProductos());
            promocion.setDescuento(promoNueva.getDescuento());
            promocionesRepository.save(promocion);
            return "La promocion "+promocionId+" fue actualizada con exito";
        }else{
            throw new RecursoNoEncontradoException("No se pudo encontrar la promocion con el Id "+promocionId);
        }
    }
}
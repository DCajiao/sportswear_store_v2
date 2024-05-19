package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.Documents.EspecificacionesArticulo;
import com.apiweb.backend.Model.Documents.ProductosPaquete;
import com.apiweb.backend.Model.ENUM.CategoriaArticulo;
import com.apiweb.backend.Model.ENUM.SeccionArticulo;
import com.apiweb.backend.Model.ENUM.TipoProducto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductosModel {
    @Id
    private ObjectId id;
    private Float precio;
    private String imagenProducto;
    private TipoProducto tipoProducto;
    private String descripcion;
    private CategoriaArticulo CategoriaArticulo;
    private SeccionArticulo seccionArticulo;
    private Integer cantidadArticulo;
    private List<EspecificacionesArticulo> especificacionesArticulo = new ArrayList<>();
    private List<ProductosPaquete> productosPaquetes = new ArrayList<>();
}

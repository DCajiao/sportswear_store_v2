package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.Documents.ProductosPromociones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Promociones")
@TypeAlias("promociones")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PromocionesModel {
    @Id
    private ObjectId id;
    private Date fechaInicio; 
    private Date fechaFin;
    private List<ProductosPromociones> productos = new ArrayList<>();
    private Float descuento;
}

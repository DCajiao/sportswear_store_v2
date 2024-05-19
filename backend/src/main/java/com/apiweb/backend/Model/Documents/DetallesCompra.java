package com.apiweb.backend.Model.Documents;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallesCompra {
    private String talla;
    private Integer cantidad;
    private ObjectId producto_id;
    private Personalizacion personalizacion;
}

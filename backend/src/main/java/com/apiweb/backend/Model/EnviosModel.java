package com.apiweb.backend.Model;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Envios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnviosModel {
    @Id
    private ObjectId id;
    private String direccion;
    private LocalDate fechaEnvio;
    private Integer codigoPostal;
    private ObjectId compra_id;
    private ObjectId lugar_id;
}

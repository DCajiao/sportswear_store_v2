package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.Documents.Cuentas;
import com.apiweb.backend.Model.ENUM.GeneroPersonas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Personas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PersonasModel {
    @Id
    private ObjectId _id;
    private String nombre;
    private String correo;
    private String telefono;
    private GeneroPersonas genero;
    private Integer edad;
    private ObjectId lugar_id;
    private List<Cuentas> cuentas = new ArrayList<>();
}


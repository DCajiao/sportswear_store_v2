package com.apiweb.backend.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.Documents.ReplicasReseñas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Reseñas")
@TypeAlias("reseñas")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReseñasModel {
    @Id
    private ObjectId id;
    private String comentario;
    private Double valoracion;
    private String cuenta_usuario;
    private Date fecha;
    private ObjectId producto_id;
    private List<ReplicasReseñas> replicas = new ArrayList<>();
}

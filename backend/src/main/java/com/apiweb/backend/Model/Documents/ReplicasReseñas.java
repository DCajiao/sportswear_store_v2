package com.apiweb.backend.Model.Documents;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReplicasReseñas {
    private String comentario;
    private Date fecha;
    private String cuenta_Usuario;
}

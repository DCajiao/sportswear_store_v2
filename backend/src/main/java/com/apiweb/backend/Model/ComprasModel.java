package com.apiweb.backend.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.Documents.DetallesCompra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Compras")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComprasModel {
    @Id
    private ObjectId id;
    private String cuenta_usuario;
    private Float costoTotal;
    private LocalDate fechaCompra;
    private List<DetallesCompra> detallesCompra = new ArrayList<>();
}

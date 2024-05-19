package com.apiweb.backend.Model.Documents;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.apiweb.backend.Model.ENUM.TipoCuenta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cuentas {
    @Id
    private String usuario;
    private TipoCuenta tipoCuenta;
    private String contraseña;
}

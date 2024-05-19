package com.apiweb.backend.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document ("Lugares")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LugaresModel {
    @Id
    private ObjectId id;
    private String nombre;
    private ObjectId subLugar_id;
}

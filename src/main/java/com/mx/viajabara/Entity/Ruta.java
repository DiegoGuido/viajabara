package com.mx.viajabara.Entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.google.gson.JsonArray;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "RUTA")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private int idRuta;

    @Column(name = "paradas")
    @JsonRawValue
    @NonNull
    private String paradas;

}

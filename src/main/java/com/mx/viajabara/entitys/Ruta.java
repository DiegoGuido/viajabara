package com.mx.viajabara.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RUTA")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private int idRuta;

    @Column(name = "paradas")
    private String paradas;

}

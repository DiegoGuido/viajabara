package com.mx.viajabara.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

@Entity
@Getter
@Setter
@NoArgsConstructor

@Table(name = "PARADA")
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parada")
    private int idParada;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "latitud")
    private String latitud;

    @Column(name = "longitud")
    private String longitud;
}

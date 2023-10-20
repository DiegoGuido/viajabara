package com.mx.viajabara.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CONDUCTOR")
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private Long idConductor;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "puntuacion")
    private float puntuacion;

    @Column(name = "correo")
    private String correo;

    @Column(name = "numero_telefono")
    private String numeroTelefono;
}

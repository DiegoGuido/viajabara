package com.mx.viajabara.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "CONDUCTOR")
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private int idConductor;

    @Column(name = "rfc")
    private String rfc;

    @Column(name = "puntuacion")
    private float puntuacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario")
    private Usuario usuario;


    @OneToMany(mappedBy = "conductor",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<Viaje> viajes;

}

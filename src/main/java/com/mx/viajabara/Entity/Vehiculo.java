package com.mx.viajabara.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "VEHICULO")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private int idVehiculo;

    @Column(name = "anio", nullable = false)
    private int anio;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "num_asientos", nullable = false)
    private int numAsientos;

    @Column(name = "alias", nullable = false)
    private String alias;

    @OneToMany(mappedBy = "vehiculo",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<Viaje> viajes;

    @Column(name = "placa")
    private String placa;
}

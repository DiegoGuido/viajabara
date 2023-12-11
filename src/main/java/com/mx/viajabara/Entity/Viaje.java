package com.mx.viajabara.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "VIAJE")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private int idViaje;

    @Column(name = "fecha_viaje")
    private LocalDate fechaViaje;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "num_asientos_disponibles")
    private int numAsientosDisponibles;

    @OneToOne
    @JoinColumn(name = "ruta")
    private Ruta ruta;

    @JsonIgnoreProperties({"viajes"})
    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @JsonIgnoreProperties({"viajes"})
    @ManyToOne
    @JoinColumn(name = "id_conductor")
    private Conductor conductor;

    @JsonIgnore
    @OneToMany(mappedBy = "viaje",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<Boleto> boletos;

    @Column(name = "viaje_iniciado")
    private boolean viajeIniciado;
}

package com.mx.viajabara.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private Date fechaViaje;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "num_asientos_disponibles")
    private int numAsientosDisponibles;

    @OneToOne
    @JoinColumn(name = "ruta")
    private Ruta ruta;

    @OneToOne
    @JoinColumn(name = "vehiculo")
    private Vehiculo vehiculo;

    @OneToOne
    @JoinColumn(name = "conductor")
    private Conductor conductor;
}

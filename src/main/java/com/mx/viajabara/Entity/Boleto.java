package com.mx.viajabara.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "BOLETO")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleto")
    private int idBoleto;

    @Column(name = "num_asiento")
    private int asiento;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "viaje")
    private Viaje viaje;

    @Column(name = "precio")
    private double precio;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subida")
    private Parada subida;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bajada")
    private Parada bajada;
}

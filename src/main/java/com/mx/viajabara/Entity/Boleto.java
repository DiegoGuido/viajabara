package com.mx.viajabara.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Table(name = "BOLETO")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleto")
    private int idBoleto;

    @Column(name = "num_asiento")
    private int asiento;

    @JsonIgnoreProperties({"boletos", "usuario"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @JsonIgnoreProperties({"boletos", "ruta", "conductor", "vehiculo"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;

    @Column(name = "precio")
    private double precio;


    private Long subida;


    private Long bajada;
}

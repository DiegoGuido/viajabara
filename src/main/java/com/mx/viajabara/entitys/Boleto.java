package com.mx.viajabara.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOLETO")
public class Boleto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleto")
    private int idBoleto;

    @Column(name = "num_asiento")
    private String asiento;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "viaje")
    private Viaje viaje;

    @Column(name = "precio")
    private int precio;
}

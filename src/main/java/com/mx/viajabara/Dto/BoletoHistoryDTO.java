package com.mx.viajabara.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Viaje;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoletoHistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleto")
    private int idBoleto;

    @Column(name = "num_asiento")
    private int asiento;

    private String viaje;

    private LocalDate fecha;

    private double precio;

    private String subida;

    private String bajada;

    private String nombreConductor;

    private String modeloAuto;

    private String hora;

}

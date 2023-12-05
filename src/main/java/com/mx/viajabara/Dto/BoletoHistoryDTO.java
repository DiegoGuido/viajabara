package com.mx.viajabara.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Viaje;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class BoletoHistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleto")
    private int idBoleto;

    @Column(name = "num_asiento")
    private int asiento;

    private String viaje;

    private Date fecha;

    private double precio;

    private String subida;

    private String bajada;

    private String nombreConductor;

    private String modeloAuto;

}

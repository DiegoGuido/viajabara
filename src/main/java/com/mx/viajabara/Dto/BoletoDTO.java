package com.mx.viajabara.Dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoletoDTO {

    private int boletoId;

    @Positive(message = "El numero de asiento debe de ser positivo")
    private int asiento;

    private Long clienteId;

    private int viajeId;

    private double precio;

    private Long subida;

    private Long bajada;


}

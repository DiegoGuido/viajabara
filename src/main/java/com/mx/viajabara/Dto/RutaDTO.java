package com.mx.viajabara.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RutaDTO {

    private int idRuta;

    @NotNull
    private List<Long> paradas;
}

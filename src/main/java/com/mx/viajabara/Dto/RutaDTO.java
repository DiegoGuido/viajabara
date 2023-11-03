package com.mx.viajabara.Dto;

import com.google.gson.JsonArray;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RutaDTO {

    @NotNull(message = "paradas: latitud es requerido")
    @NotBlank(message = "paradas: latitud es NULL")
    private Object paradas;
}

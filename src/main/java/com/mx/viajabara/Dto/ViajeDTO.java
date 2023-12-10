package com.mx.viajabara.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class ViajeDTO {
    @Id
    private int idViaje;

    @NotBlank(message = "Nombre invalido: Campo requerido")
    @NotNull(message = "Nombre invalido: nombre es NULL")
    @Size(min = 3, max = 30, message = "Nombre invalido: La longitud debe ser de entre 3 y 30 caracteres")
    private String nombre;

    @NotNull(message = "asientos invalidos: Campo requerido")
    private int num_asientos_disponibles;

    @JsonFormat(pattern = "yyyy-MM-dd")

    @FutureOrPresent(message = "Fecha de viaje incorrecta")
    private LocalDate fechaViaje;

    @NotNull(message = "ruta invalida: Campo requerido")
    private Long ruta;


    @NotNull(message = "vehiculo invalido: Campo requerido")
    private Long vehiculo;


    @NotNull(message = "conductor invalido: Campo requerido")
    private int conductor;

    private boolean viajeIniciado;
}

package com.mx.viajabara.Dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParadaDTO {


    private Long idParada;

    @NotBlank(message = "Nombre invalido: Campo requerido")
    @NotNull(message = "Nombre invalido: nombre es NULL")
    @Size(min = 3, max = 30, message = "Nombre invalido: el nombre tiene que tener una longitud de 3 - 30 caracteres")
    private String nombre;


    private String descripcion;

    @NotNull(message = "Latitud invalida: latitud es requerido")
    @NotBlank(message = "Latitud invalida: latitud es NULL")
    private String latitud;

    @NotNull(message = "Longitud invalida: longitud es requerido")
    @NotBlank(message = "Longitud invalida: longitud es NULL")
    private String longitud;

}

package com.mx.viajabara.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @Email(message = "Correo invalido")
    @NotNull(message = "Correo invalido: este campo no puede ser nulo")
    @NotBlank(message = "Correo invalido: Campo requerido")
    private String correo;

    @NotBlank(message = "Clave invalida: Campo requerido")
    @NotNull(message = "Clave invalido: este campo no puede ser nulo")
    @Size(min = 6, message = "Clave invalida: Debe tener al menos 6 caracteres")
    private String clave;

}

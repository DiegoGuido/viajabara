package com.mx.viajabara.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Id
    private int idCliente;

    @NotBlank(message = "Nombre invalido: Campo requerido")
    @NotNull(message = "Nombre invalido: nombre es NULL")
    @Size(min = 3, max = 30, message = "Nombre invalido: La longitud debe ser de entre 3 y 30 caracteres")
    private String nombre;

    @Email(message = "Correo invalido")
    @NotNull(message = "Correo invalido: este campo no puede ser nulo")
    @NotBlank(message = "Correo invalido: Campo requerido")
    private String correo;

    @NotBlank(message = "Clave invalida: Campo requerido")
    @NotNull(message = "Clave invalido: este campo no puede ser nulo")
    @Size(min = 6, message = "Clave invalida: Debe tener al menos 6 caracteres")
    private String clave;

    private String fotoPerfil;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Past(message = "Fecha de nacimiento invalida: Debe ser en el pasado")
    private Date fechaNacimiento;

}

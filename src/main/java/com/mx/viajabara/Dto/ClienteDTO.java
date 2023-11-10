package com.mx.viajabara.Dto;

import com.mx.viajabara.Entity.Boleto;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ClienteDTO {
    private Long idCliente;

    @NotNull(message = "Nombre invalido: Nombre es requerido")
    @NotBlank(message = "Nombre invalido: Nombre es NULL")
    private String nombre;

    @NotNull(message = "Correo invalido: Correo es requerido")
    @NotBlank(message = "Correo invalido: Correo es NULL")
    @Email
    private String correo;

    @NotNull(message = "Clave invalido: Clave es requerido")
    @NotBlank(message = "Clave invalido: Clave es NULL")
    private String clave;


    private String fotoPerfil;


    private Date fechaNacimiento;


    private List<Boleto> boletos;
}

package com.mx.viajabara.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "clave")
    private String clave;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "cliente")
    private List<Boleto> boletos;

}

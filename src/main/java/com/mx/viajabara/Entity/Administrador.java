package com.mx.viajabara.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "ADMINISTRADOR")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_administrador")
    private int idAdministrador;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario")
    private Usuario usuario;
}

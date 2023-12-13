package com.mx.viajabara.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "CLIENTE")
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "viaja_activo")
    private boolean viajeActivo;

    @OneToMany(mappedBy = "cliente",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true)
    private Set<Boleto> boletos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario")
    private Usuario usuario;

}

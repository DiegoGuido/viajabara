package com.mx.viajabara.Dto;

import com.mx.viajabara.Entity.Boleto;
import com.mx.viajabara.Entity.Cliente;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarViajeDTO {

    private List<Boleto> clientesASubir;

    private List<Boleto> clientesABajar;
}

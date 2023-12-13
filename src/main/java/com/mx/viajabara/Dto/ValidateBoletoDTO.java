package com.mx.viajabara.Dto;

import com.mx.viajabara.Entity.Boleto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
@Getter
@Setter
public class ValidateBoletoDTO {

    private int idViaje;

    List<Boleto> boletoList;

    private boolean validadosCorrect;
}

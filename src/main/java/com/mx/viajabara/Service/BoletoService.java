package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.BoletoDTO;
import com.mx.viajabara.Dto.ValidateBoletoDTO;
import com.mx.viajabara.Entity.Boleto;
import com.mx.viajabara.Entity.Response;

public interface BoletoService {

    Response getAll();
    Response deleteById(int id);
    Response saveBoleto(BoletoDTO boleto);
    Response getById(int id);
    Response validateBoletos(ValidateBoletoDTO validateBoletoDTO);
    Response getAsientosDisponibles(int idViaje, Long paradaSubir, Long paradaBajar);
    Response getCantidadParadas(Long idSubida, Long idBajada, int idViaje );
}

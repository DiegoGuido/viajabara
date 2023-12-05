package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.BoletoDTO;
import com.mx.viajabara.Entity.Boleto;
import com.mx.viajabara.Entity.Response;

public interface BoletoService {

    Response getAll();
    Response deleteById(int id);
    Response saveBoleto(BoletoDTO boleto);
    Response getById(int id);

}

package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Response;

import java.util.List;

public interface ParadaService {

    Response saveOrUpdateParada(ParadaDTO parada);

    Response getAll();

    Response deleteParada(Long id);

    Response getParadaById(Long id);

}

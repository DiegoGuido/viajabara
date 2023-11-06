package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Response;

import java.util.List;

public interface ParadaService {

    Boolean saveOrUpdateConductor(ParadaDTO parada);

    List<Parada> getAll();

    String deleteParada(Long id);

    Response getParadaById(Long id);

}

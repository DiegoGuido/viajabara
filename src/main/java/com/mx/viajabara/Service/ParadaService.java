package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Entity.Parada;

import java.util.List;

public interface ParadaService {

    Boolean saveOrUpdateConductor(ParadaDTO parada);

    List<Parada> getAll();

    String deleteParada(Long id);

    Parada getParadaById(Long id);

}

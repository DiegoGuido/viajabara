package com.mx.viajabara.Service;

import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Entity.Parada;

import java.util.List;

public interface ParadaService {

    Boolean saveOrUpdateConductor(Parada parada);

    List<Parada> getAll();

    Boolean deleteParada(Long id);

    Parada getParadaById(Long id);

}

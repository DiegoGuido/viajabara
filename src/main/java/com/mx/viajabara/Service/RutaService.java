package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ParadaDTO;

import com.mx.viajabara.Dto.RutaDTO;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Ruta;

import java.util.List;

public interface RutaService {

    Response saveOrUpdateRuta(RutaDTO ruta);

    Response getAll();

    Response deleteById(Long id);

    Response findById(Long id);

}

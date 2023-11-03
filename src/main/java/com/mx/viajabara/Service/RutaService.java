package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Dto.RutaDTO;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Ruta;

import java.util.List;

public interface RutaService {

    Boolean saveOrUpdateRuta(Ruta ruta);

}

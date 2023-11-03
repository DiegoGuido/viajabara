package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.RutaDTO;
import com.mx.viajabara.Entity.Ruta;
import com.mx.viajabara.Repository.RutaRepository;
import com.mx.viajabara.Service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RutaServiceImpl implements RutaService {

    @Autowired
    RutaRepository rutaRepository;

    @Override
    public Boolean saveOrUpdateRuta(Ruta ruta){
        rutaRepository.save(ruta);
        return true;
    }
}

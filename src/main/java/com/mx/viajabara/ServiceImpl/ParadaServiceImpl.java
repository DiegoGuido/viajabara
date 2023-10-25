package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Repository.ParadaRepository;
import com.mx.viajabara.Service.ParadaService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParadaServiceImpl implements ParadaService {

    @Autowired
    ParadaRepository paradaRepository;

    @Override
    public Boolean saveOrUpdateConductor(Parada parada) {
        Boolean response = false;

        Parada saved = paradaRepository.save(parada);

        if (saved != null) response = true;

        return response;
    }

    @Override
    public List<Parada> getAll() {
        return paradaRepository.findAll();
    }

    @Override
    public Boolean deleteParada(Long id) {
        paradaRepository.deleteById(id);
        if (getParadaById(id) == null){
            return true;
        }
        return null;
    }

    @Override
    public Parada getParadaById(Long id) {
        Optional<Parada> parada = paradaRepository.findById(id);
        if (parada.isPresent()){
            return parada.get();
        }
        return null;
    }
}

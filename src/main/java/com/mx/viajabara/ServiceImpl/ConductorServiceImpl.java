package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Repository.ConductorRepository;
import com.mx.viajabara.Service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    ConductorRepository conductorRepository;

    @Override
    public Boolean saveOrUpdateConductor(Conductor conductor) {
        Boolean response = false;

        Conductor conductorSave = conductorRepository.save(conductor);

        if (conductorSave != null) response = true;

        return response;
    }

    @Override
    public List<Conductor> getAll() {
        List<Conductor> conductors = conductorRepository.findAll();
        return conductors;
    }

    @Override
    public Boolean deleteConductor(Long id) {
        conductorRepository.deleteById(id);
        if (getConductorById(id) == null){
            return true;
        }
        return false;
    }

    @Override
    public Conductor getConductorById(Long id){
        Optional<Conductor> conductor = conductorRepository.findById(id);
        if (conductor.isPresent()){
            return conductor.get();
        }
        return null;
    }
}

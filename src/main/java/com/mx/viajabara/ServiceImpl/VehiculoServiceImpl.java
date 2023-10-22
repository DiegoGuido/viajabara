package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Entity.Vehiculo;
import com.mx.viajabara.Repository.VehiculoRepository;
import com.mx.viajabara.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired

    VehiculoRepository vehiculoRepository;

    @Override
    public Boolean saveOrUpdateVehiculo(Vehiculo vehiculo) {
        Boolean response = false;

        Vehiculo vehiculoSave = vehiculoRepository.save(vehiculo);

        if (vehiculoSave != null){
            response = true;
        }

        return response;

    }

    @Override
    public List<Vehiculo> getAll() {
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();
        return vehiculos;
    }

    @Override
    public Vehiculo getVehiculoById(Long id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        return vehiculo.orElse(null);
    }

    @Override
    public Boolean deleteVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
        return getVehiculoById(id) == null;
    }
}

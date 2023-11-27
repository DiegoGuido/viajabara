package com.mx.viajabara.Service;

import com.mx.viajabara.Entity.Vehiculo;

import java.util.List;

public interface VehiculoService {

    Boolean saveOrUpdateVehiculo(Vehiculo vehiculo);

    List<Vehiculo> getAll();

    Vehiculo getVehiculoById(Long id);

    Boolean deleteVehiculo(Long id);


}

package com.mx.viajabara.Service;

import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Vehiculo;

import java.util.List;

public interface VehiculoService {

    Response saveOrUpdateVehiculo(Vehiculo vehiculo);

    Response getAll();

    Response getVehiculoById(Long id);

    Response deleteVehiculo(Long id);


}

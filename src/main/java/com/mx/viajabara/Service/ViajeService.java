package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ViajeDTO;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Viaje;

public interface ViajeService {

    Response getAll();

    Response createOrUpdateViaje(ViajeDTO viaje);

    Response deleteViajebyId(int id);

    Response getViajeById(int id);
}

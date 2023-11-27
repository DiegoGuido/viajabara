package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ConductorDTO;
import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Entity.Response;

import java.util.ArrayList;
import java.util.List;

public interface ConductorService {

    Response saveOrUpdateConductor(ConductorDTO conductor);

    Response getAll();

    Response deleteConductor(int id);

    Response getConductorById(int id);

}

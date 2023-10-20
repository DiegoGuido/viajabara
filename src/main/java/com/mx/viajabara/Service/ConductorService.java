package com.mx.viajabara.Service;

import com.mx.viajabara.Entity.Conductor;

import java.util.ArrayList;
import java.util.List;

public interface ConductorService {

    Boolean saveOrUpdateConductor(Conductor conductor);

    List<Conductor> getAll();

    Boolean deleteConductor(Long id);

    Conductor getConductorById(Long id);

}

package com.mx.viajabara.Controller;

import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Service.ConductorService;
import com.mx.viajabara.ServiceImpl.ConductorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/conductor")
public class ConductorController {

    @Autowired
    private ConductorServiceImpl conductorService;

    @GetMapping(value = "/")
    public List<Conductor> getAll(){
        List<Conductor> conductores = conductorService.getAll();
        return conductores;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Conductor> getConductorById(@PathVariable(name = "id") Long id){
        Conductor conductor = conductorService.getConductorById(id);
        if (conductor != null){
            return ResponseEntity.ok(conductor);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Boolean> saveConductor(@RequestBody Conductor conductor){
        Boolean creado = conductorService.saveOrUpdateConductor(conductor);
        return ResponseEntity.ok(creado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteConductorById(@PathVariable(name = "id") Long id){
        Boolean eliminado = conductorService.deleteConductor(id);
        return ResponseEntity.ok(eliminado);
    }
}

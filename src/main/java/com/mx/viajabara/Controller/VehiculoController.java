package com.mx.viajabara.Controller;


import com.mx.viajabara.Entity.Vehiculo;
import com.mx.viajabara.ServiceImpl.VehiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoServiceImpl vehiculoService;

    @GetMapping(value = "/")
    public List<Vehiculo> getAll(){
        return vehiculoService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable(name = "id") Long id){
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);
        if (vehiculo != null){
            return ResponseEntity.ok(vehiculo);
        }
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Boolean> saveVehiculo(@RequestBody Vehiculo vehiculo){
        Boolean created = vehiculoService.saveOrUpdateVehiculo(vehiculo);
        return ResponseEntity.ok(created);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteVehiculoById(@PathVariable(name = "id") Long id){
        Boolean deleted = vehiculoService.deleteVehiculo(id);
        return ResponseEntity.ok(deleted);
    }


}

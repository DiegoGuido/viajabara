package com.mx.viajabara.Controller;


import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Vehiculo;
import com.mx.viajabara.ServiceImpl.VehiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/vehiculo")
public class VehiculoController {

    @Autowired
    private VehiculoServiceImpl vehiculoService;

    @GetMapping(value = "/")
    public ResponseEntity<Response> getAll(){
        Response response = new Response();
        try {
            response = vehiculoService.getAll();
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getVehiculoById(@PathVariable(name = "id") Long id){
        Response response = new Response();
        try {
            response = vehiculoService.getVehiculoById(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

    @PostMapping(value = "/")
    public ResponseEntity<Response> saveVehiculo(@RequestBody Vehiculo vehiculo){
        Response response = new Response();
        try {
            response = vehiculoService.saveOrUpdateVehiculo(vehiculo);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteVehiculoById(@PathVariable(name = "id") Long id){
        Response response = new Response();
        try {
            response = vehiculoService.deleteVehiculo(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }


}

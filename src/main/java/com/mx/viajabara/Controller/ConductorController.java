package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.ConductorDTO;
import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Service.ConductorService;
import com.mx.viajabara.ServiceImpl.ConductorServiceImpl;
import jakarta.validation.Valid;
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
    public ResponseEntity<Response> getAll(){
        Response response = null;
        try{
            response = conductorService.getAll();
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getConductorById(@PathVariable(name = "id") int id){
        Response response = new Response();
        try {
            response = conductorService.getConductorById(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteConductorById(@PathVariable(name = "id") int id){
        Response response = new Response();
        try {
            response = conductorService.deleteConductor(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.mx.viajabara.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Ruta;
import com.mx.viajabara.ServiceImpl.RutaServiceImpl;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Set;

@RestController
@RequestMapping(value = "/ruta")
public class RutaController {

    @Autowired
    RutaServiceImpl rutaService;

    @PostMapping("/")
    public ResponseEntity<Response> saveOrUpdate(@RequestBody Object ruta) {
        Response response = new Response();
        try{
            response = rutaService.saveOrUpdateRuta(ruta);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<Response> getAll(){
        Response response = new Response();
        try {
            response = rutaService.getAll();
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable(name = "id") Long id){
        Response response = new Response();
        try {
            response = rutaService.deleteById(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> findByid(@PathVariable(name = "id") Long id){
        Response response = new Response();
        try {
            response = rutaService.findById(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

}

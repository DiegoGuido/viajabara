package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Service.ParadaService;
import com.mx.viajabara.ServiceImpl.ParadaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parada")
public class ParadaController {

    @Autowired
    ParadaServiceImpl paradaService;

    @GetMapping(value = "/")
    public ResponseEntity<Response> getAll(){
        Response response = new Response();
        try {
            List<Parada> paradas = paradaService.getAll();
            response.setMessage("Ok");
            response.setObject(paradas);
        }catch (Exception e){
            response.setMessage("Error al consultar las paradas");
            response.setObject(null);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Response> saveOrUpdate(@RequestBody @Valid ParadaDTO parada){
        Boolean saved = false;
        Response response = new Response();
        try{
             saved = paradaService.saveOrUpdateConductor(parada);
            if (saved){
                response.setMessage("Guardado correctamente");
                response.setObject(saved);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        }catch (Exception e){
            response.setMessage("Error al guardar - Consulte a su administrador");
            response.setObject(saved);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getParadaById(@PathVariable(name = "id") Long id){
        Response response = new Response();
        try{
            response = paradaService.getParadaById(id);
            if (response.getError()){
                return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        }catch(Exception e){
            return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable(name = "id") Long id){
        Response response = new Response();
        try{
            String deleted = paradaService.deleteParada(id);
            response.setMessage("Ok");
            response.setObject(deleted);
        }catch (Exception e){
            response.setMessage("Problemas para eliminar la parada id -" + id);
            response.setObject(id);
        }
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }
}

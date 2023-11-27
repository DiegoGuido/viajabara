package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.ViajeDTO;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Viaje;
import com.mx.viajabara.ServiceImpl.ViajeServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/viajes")
public class ViajesController {

    @Autowired
    ViajeServiceImpl viajeService;
    @GetMapping("/")
    public ResponseEntity<Response> getAllViajes(){
        Response response  = new Response();
        try {
            response = viajeService.getAll();
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/")
    public ResponseEntity<Response> saveOrUpdate(@RequestBody @Valid ViajeDTO viaje){
        Response response = new Response();
        try {
            response = viajeService.createOrUpdateViaje(viaje);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

   @DeleteMapping("/{id}")
   public ResponseEntity<Response> deleteById(@PathVariable(name = "id") Integer id){
       Response response = new Response();
       try {
           response = viajeService.deleteViajebyId(id);
           if (response.getError()){
               return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
           }
       }catch (Exception e){
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Response> findByid(@PathVariable(name = "id") Integer id){
       Response response = new Response();
       try {
           response = viajeService.getViajeById(id);
           if (response.getError()){
               return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
           }
       }catch (Exception e){
           return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
       return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
   }


}

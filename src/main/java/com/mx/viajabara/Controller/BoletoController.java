package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.BoletoDTO;
import com.mx.viajabara.Dto.ValidateBoletoDTO;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.ServiceImpl.BoletoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/boleto")
public class BoletoController {

    @Autowired
    BoletoServiceImpl boletoService;
    @PostMapping("/")
    public ResponseEntity<Response> saveBoleto(@RequestBody @Valid BoletoDTO boletoDTO){
        Response response = new Response();
        try {
            response = boletoService.saveBoleto(boletoDTO);
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
            response = boletoService.getAll();
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable(name = "id") int id){
        Response response = new Response();
        try {
            response = boletoService.deleteById(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Response> getById(@PathVariable(name = "id") int id){
        Response response = new Response();
        try {
            response = boletoService.getById(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("validar/")
    ResponseEntity<Response> validateBoletos(@RequestBody ValidateBoletoDTO validateBoletoDTODTO){
        Response response = new Response();
        try {
            response = boletoService.validateBoletos(validateBoletoDTODTO);
        }catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{idViaje}/{idParada}")
    ResponseEntity<Response> getAsientosOcupados(@PathVariable(name = "idViaje") int idViaje,
                                                 @PathVariable(name = "idParada") Long idParada){
        Response response = new Response();
        try {
            response = boletoService.getAsientosDisponibles(idViaje, idParada, idParada);
        }catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

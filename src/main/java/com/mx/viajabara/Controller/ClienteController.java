package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Dto.LoginDTO;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Usuario;
import com.mx.viajabara.ServiceImpl.ClienteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;



    @GetMapping(value = "/")
        public ResponseEntity<Response> getAll(){
        try {
            Response response = clienteService.getAll();
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new Response(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getClienteById(@PathVariable(name = "id") Long id){
        try{
            Response response = clienteService.getClienteById(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new Response(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/boletos/{id}")
    public ResponseEntity<Response> getBoletosByCliente(@PathVariable(name = "id") Long id){
        try{
            Response response = clienteService.getBoletosByCliente(id);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            return new ResponseEntity<>(new Response(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @PostMapping(value = "/")
    public ResponseEntity<Response> saveCliente(@RequestBody @Valid ClienteDTO cliente){
        Response response = new Response();
        try{
            response = clienteService.saveOrUpdateClient(cliente);
            if (response.getError()) {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }*/



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Response> deleteClienteById(@PathVariable(name = "id") Long id) {
        try {
            Response response = clienteService.deleteCliente(id);
            if (response.getError()) {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Response(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Dto.ConductorDTO;
import com.mx.viajabara.Dto.LoginDTO;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Usuario;
import com.mx.viajabara.ServiceImpl.ClienteServiceImpl;
import com.mx.viajabara.ServiceImpl.ConductorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    ClienteServiceImpl clienteServiceImpl;

    @Autowired
    ConductorServiceImpl conductorService;

    @PostMapping(value = "/login")
    public ResponseEntity<Response> login(@RequestBody LoginDTO loginDTO){
        try{
            Response response = clienteServiceImpl.login(loginDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Response> saveUser(@RequestBody @Valid ClienteDTO cliente){
        Response response = new Response();
        try{
            response = clienteServiceImpl.saveUser(cliente);
            if (response.getError()) {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "conductor/")
    public ResponseEntity<Response> saveConductor(@RequestBody @Valid ConductorDTO conductor){
        Response response = new Response();
        try {
            response = conductorService.saveOrUpdateConductor(conductor);
            if (response.getError()){
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

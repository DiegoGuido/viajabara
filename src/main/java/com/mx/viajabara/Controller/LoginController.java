package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.LoginDTO;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Usuario;
import com.mx.viajabara.ServiceImpl.ClienteServiceImpl;
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

    @PostMapping(value = "/login")
    public ResponseEntity<Cliente> login(@RequestBody LoginDTO loginDTO){
        try{
            Cliente currentClient = clienteServiceImpl.login(loginDTO);
            return new ResponseEntity<>(currentClient, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Response> saveUser(@RequestBody Usuario cliente){
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

}

package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.LoginDTO;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.ServiceImpl.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    ClienteServiceImpl clienteServiceImpl;

    @PostMapping(value = "/login")
    public ResponseEntity<Cliente> login(@ModelAttribute LoginDTO loginDTO){
        try{
            Cliente currentClient = clienteServiceImpl.login(loginDTO);
            return new ResponseEntity<>(currentClient, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}

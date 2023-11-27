package com.mx.viajabara.Controller;

import com.mx.viajabara.Dto.BoletoDTO;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.ServiceImpl.BoletoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

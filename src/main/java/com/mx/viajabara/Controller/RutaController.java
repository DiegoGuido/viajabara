package com.mx.viajabara.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Dto.RutaDTO;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Ruta;
import com.mx.viajabara.ServiceImpl.RutaServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ruta")
public class RutaController {

    @Autowired
    RutaServiceImpl rutaService;
    @PostMapping("/")
    public ResponseEntity<Boolean> saveOrUpdate(@RequestBody Object ruta) {
        try{

        }catch (Exception e){

        }
        JsonObject jsonObject = new JsonParser().parse(new Gson().toJson(ruta)).getAsJsonObject();
        System.out.println(jsonObject.getAsJsonArray("paradas"));

    return new ResponseEntity<>(rutaService.saveOrUpdateRuta(new Ruta()), new HttpHeaders(), HttpStatus.OK);
    }

}

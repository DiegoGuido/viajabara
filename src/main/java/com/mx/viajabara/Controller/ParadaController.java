package com.mx.viajabara.Controller;

import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Service.ParadaService;
import com.mx.viajabara.ServiceImpl.ParadaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parada")
public class ParadaController {

    @Autowired
    ParadaServiceImpl paradaService;

    @GetMapping(value = "/")
    public Response getAll(){
        Response response = new Response();
        try {
            List<Parada> paradas = paradaService.getAll();
            response.setMessage("Okay");
            response.setObject(paradas);
        }catch (Exception e){
            response.setMessage("Error al consultar las paradas");
            response.setObject(null);
        }
        return response;
    }

    @PostMapping("/")
    public Response saveOrUpdate(@RequestBody Parada parada){
        Response response = new Response();
        try{
            Boolean saved = paradaService.saveOrUpdateConductor(parada);
            if (saved){
                response.setMessage("Parada guardada correctamenta - " + parada.getNombre());
                response.setObject(parada);
            }
        }catch (Exception e){
            response.setMessage("Problemas al guardar la parada - " + parada.getNombre());
            response.setObject(parada);
        }
        return response;
    }

    @GetMapping(value = "/{id}")
    public Response getParadaById(@PathVariable(name = "id") Long id){
        Response response = new Response();
        try{
           Parada parada = paradaService.getParadaById(id);
           response.setMessage("Ok");
           response.setObject(parada);
        }catch(Exception e){
            response.setMessage("Problemas para obtener la parada id -" + id);
            response.setObject(id);
        }
        return response;
    }

    @DeleteMapping(value = "/{id}")
    public Response deleteById(@PathVariable(name = "id") Long id){
        Response response = new Response();
        try{
            Boolean deleted = paradaService.deleteParada(id);
            if (deleted){
                response.setMessage("Eliminado");
            }else {
                response.setMessage("No se elimino");
            }
            response.setObject(deleted);
        }catch (Exception e){
            response.setMessage("Problemas para eliminar la parada id -" + id);
            response.setObject(id);
        }
        return response;
    }
}

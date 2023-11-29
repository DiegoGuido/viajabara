package com.mx.viajabara.ServiceImpl;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Dto.RutaDTO;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Ruta;
import com.mx.viajabara.Repository.RutaRepository;
import com.mx.viajabara.Service.RutaService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RutaServiceImpl implements RutaService {

    @Autowired
    RutaRepository rutaRepository;

    @Autowired
    ParadaServiceImpl paradaService;

    @Override
    public Response deleteById(Long id){
        Response response = new Response();
        try {
            rutaRepository.deleteById(id);
            Response eliminado = findById(id);
            if (eliminado.getError() && eliminado.getObject() == null){
                response.setMessage("Eliminado Correctamente");
                response.setError(false);
                response.setObject(id);
            }else {
                response.setMessage("Problemas al eliminar la ruta con id: " + id);
                response.setError(true);
                response.setObject(id);
            }
        }catch (Exception e){
            response.setMessage("Hubo un error al querer eliminar la ruta intente más tarde o comuniquese con el administrador" );
            response.setError(true);
            response.setObject(new Error());
        }

        return response;
    }

    @Override
    public Response findById(Long id){
        Response response = new Response();
        try {
            Optional<Ruta> ruta = rutaRepository.findById(id);
            if (ruta.isPresent()){
                response.setMessage("Ok");
                response.setError(false);
                response.setObject(ruta.get());

            }else {
                response.setMessage("No se encontro la ruta con el id: " + id);
                response.setError(true);
                response.setObject(null);
            }
        }catch (Exception e){
            response.setMessage("Hubo un error al querer consultar la ruta intente más tarde o comuniquese con el administrador" );
            response.setError(true);
            response.setObject(new Error());
        }
        return response;
    }

    @Override
    public Response getAll(){
        Response response = new Response();
        try {
            List<Ruta> rutasList = rutaRepository.findAll();
            response.setError(false);
            response.setObject(rutasList);
            if (rutasList == null){
                response.setMessage("No hay rutas");
            }else {
                response.setMessage("Ok");
            }
        }catch (Exception e){
            response.setMessage("Hubo un error al querer consultar todas las rutas intente más tarde o comuniquese con el administrador" );
            response.setError(true);
            response.setObject(null);
        }

        return response;
    }

    @Override
    public Response saveOrUpdateRuta(RutaDTO ruta){
        try{
            List<Long> idParadas = ruta.getParadas();
            List<Parada> paradas = new ArrayList<>();
                for (Long idParada: idParadas) {
                    Response paradaTmp =  paradaService.getParadaById(idParada);
                    if (paradaTmp.getError()){
                        return paradaTmp;
                    }else {
                        paradas.add((Parada) paradaTmp.getObject());
                    }
                }

                Ruta rutaSave = Ruta.builder().idRuta(ruta.getIdRuta()).paradas(paradas).build();

                Ruta rutaSaved = rutaRepository.save(rutaSave);

                if (rutaSaved !=null){
                    return new Response("Ruta guardada exitosamente", rutaSaved, false);
                }else {
                    return new Response("Hubo problemas al querer guardar el objeto", rutaSaved, true);
                }
        }catch (Exception e){
            System.out.println(e);
            return new Response("Hubo problemas al ejecutar el método save, intente más tarde o comuniquese con el administrador", null, true);
        }
    }

}

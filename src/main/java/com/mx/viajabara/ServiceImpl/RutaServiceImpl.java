package com.mx.viajabara.ServiceImpl;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.mx.viajabara.Dto.ParadaDTO;
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
                response.setObject(ruta);

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
    public Response saveOrUpdateRuta(Object ruta){
        Gson gson = new Gson();
        Response response = new Response();

        try {
            //Obtenemos el array de paradas designadas en la ruta
            JsonArray jsonObject = JsonParser.parseString(new Gson().toJson(ruta)).getAsJsonObject().getAsJsonArray("paradas");
            //Convertimos el objeto Json a un Array de paradas
            ParadaDTO[] paradaArray = new Gson().fromJson(jsonObject, ParadaDTO[].class);

            //Después de eso validamos las paradas que vengan en el array
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            //Se valida que las paradas sean correctas
            List<ConstraintViolation<ParadaDTO>> violationsGeneral = new LinkedList<>();

            for (ParadaDTO parada: paradaArray){
                Set<ConstraintViolation<ParadaDTO>> violations = validator.validate(parada);
                violationsGeneral.addAll(violations);
            }

            if (violationsGeneral.isEmpty()){
                for (ParadaDTO paradaDTO: paradaArray){
                    Parada parada = Parada.builder()
                            .idParada(paradaDTO.getIdParada())
                            .nombre(paradaDTO.getNombre())
                            .descripcion(paradaDTO.getDescripcion())
                            .latitud(paradaDTO.getLatitud())
                            .longitud(paradaDTO.getLongitud()).build();

                    Response paradaEncontrada = paradaService.getParadaById(parada.getIdParada());
                    if (paradaEncontrada.getError()){
                        return paradaEncontrada;
                    }
                }

                Ruta rutaGuardada = rutaRepository.save(new Ruta(gson.toJson(ruta))
                );

                if (rutaGuardada != null){
                    response.setObject(rutaGuardada);
                    response.setError(false);
                    response.setMessage("Ruta guardada con exito");
                }else {
                    response.setObject(rutaGuardada);
                    response.setError(true);
                    response.setMessage("Problemas al guardar la ruta, intentelo más tarde o contacte al administrador");
                }

            }else {
                HashMap<String, List<String>> errorResponse = new HashMap<>();
                List<String> errors = new LinkedList<>();
                for (ConstraintViolation<ParadaDTO> violation : violationsGeneral){
                    errors.add(violation.getMessage() + " id: "+ violation.getRootBean().getIdParada());
                }
                errorResponse.put("Errors", errors);
                response.setObject(errorResponse);
                response.setMessage("Paradas invalidas");
                response.setError(true);
            }

        }catch (Exception e){
            response.setObject(null);
            response.setError(true);
            response.setMessage("Problemas al ejecutar el método para guardar la ruta, intentelo más tarde o contacte al administrador");
            System.out.println(e);
        }
        return response;
    }
}

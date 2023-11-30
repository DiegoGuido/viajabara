package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.ParadaDTO;
import com.mx.viajabara.Entity.Parada;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Repository.ParadaRepository;
import com.mx.viajabara.Service.ParadaService;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParadaServiceImpl implements ParadaService {

    @Autowired
    ParadaRepository paradaRepository;

    @Override
    public Response saveOrUpdateParada(ParadaDTO paradaDto) {
        try {
            Parada parada = Parada.builder()
                    .idParada(paradaDto.getIdParada())
                    .nombre(paradaDto.getNombre())
                    .descripcion(paradaDto.getDescripcion())
                    .latitud(paradaDto.getLatitud())
                    .longitud(paradaDto.getLongitud()).build();
            Parada paradaSaved = paradaRepository.save(parada);
            if (paradaSaved != null){
                return new Response("Ok", paradaSaved, false);
            }else{
                return new Response("Hubo un problema al querer guardar la parada", paradaSaved, true);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al querer ejecutar el método de guardar parada, intentelo más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response getAll() {
        try{
            List<Parada> paradasList = paradaRepository.findAll();
            if (paradasList.isEmpty()){
                return new Response("No hay paradas disponibles", null, true);
            }else {
                return new Response("Ok", paradasList, false);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al querer ejecutar el método para obtener las paradas, intentelo más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response deleteParada(Long id) {
        try {
            if (getParadaById(id)!= null){
                paradaRepository.deleteById(id);
                Response eliminado = getParadaById(id);
                if (eliminado.getError() && eliminado.getObject() == null){
                    return new Response("Ok", id, false);
                }else {
                    return new Response("No se pudo eliminar la parada con el id " + id, id, true);
                }
            }else{
                return new Response("El id: " + id +" no existe", id, true);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al querer ejecutar el método para eliminar la parada, intentelo más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response getParadaById(Long id) {
        Response response = new Response();
        try {
            Optional<Parada> parada = paradaRepository.findById(id);
            if (parada.isPresent()){
                response.setMessage("Ok");
                response.setError(false);
                response.setObject(parada.get());

            }else {
                response.setMessage("No se encontro la parada con el id: " + id);
                response.setError(true);
                response.setObject(null);
            }
        }catch (Exception e){
            response.setMessage("Hubo un error al querer consultar la parada intente más tarde o comuniquese con el administrador" );
            response.setError(true);
            response.setObject(null);
        }
        return response;
    }
}

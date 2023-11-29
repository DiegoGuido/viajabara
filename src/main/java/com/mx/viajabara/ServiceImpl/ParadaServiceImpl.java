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
    public Boolean saveOrUpdateConductor(ParadaDTO paradaDto) {
        Boolean response = false;
        Parada parada = Parada.builder()
                .idParada(paradaDto.getIdParada())
                .nombre(paradaDto.getNombre())
                .descripcion(paradaDto.getDescripcion())
                .latitud(paradaDto.getLatitud())
                .longitud(paradaDto.getLongitud()).build();

        Parada saved = paradaRepository.save(parada);

        if (saved != null) response = true;

        return response;
    }

    @Override
    public List<Parada> getAll() {
        return paradaRepository.findAll();
    }

    @Override
    public String deleteParada(Long id) {
        if (getParadaById(id)!= null){
            paradaRepository.deleteById(id);
            if (getParadaById(id) == null){
                return "Se elimino la parada con ID " + id;
            }
        }else{
            return "El ID " +id +" no existe";
        }
        return null;
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

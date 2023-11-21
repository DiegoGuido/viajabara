package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.ViajeDTO;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Viaje;
import com.mx.viajabara.Repository.ViajeRepository;
import com.mx.viajabara.Service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ViajeServiceImpl implements ViajeService {

    @Autowired
    ViajeRepository viajeRepository;
    @Autowired
    RutaServiceImpl rutaService;
    @Autowired
    VehiculoServiceImpl vehiculoService;
    @Autowired
    ConductorServiceImpl conductorService;
    @Override
    public Response getAll() {
        try {
            List<Viaje> viajes = viajeRepository.findAll();
            if (viajes != null){
                return new Response("Ok", viajes, false);
            }else {
                return new Response("No hay viajes disponibles", viajes, false);
            }
        }catch (Exception e){
            return new Response("Hubo un error al querer consultar todos los viajes, intente más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response createOrUpdateViaje(ViajeDTO viaje) {
        try{
            Viaje viajeValidado =
                    Viaje.builder()
                    .idViaje(viaje.getIdViaje())
                            .fechaViaje(viaje.getFechaViaje())
                            .nombre(viaje.getNombre())
                            .ruta(rutaService.rutaRepository.findById(viaje.getRuta()).get())
                            .vehiculo(vehiculoService.vehiculoRepository.findById(viaje.getVehiculo()).get())
                            .conductor(conductorService.conductorRepository.findById(viaje.getConductor()).get())
                            .build();

            Viaje viajeGuardado = viajeRepository.save(viajeValidado);
            if (viajeGuardado !=null){
                return new Response("Viaje guardado con exito", viajeGuardado, false);
            }else {
                return new Response("Error al guardar el viaje", viajeGuardado, true);
            }
        }catch (DataIntegrityViolationException e){
            return new Response("El conductor o el vehiculo ya tienen un viaje asignado", null, true);
        }
        catch (NoSuchElementException e){
            return new Response("Alguno de los datos ingresados no existe (Conductor, Vehiculo, Ruta)", null, true);
        }
        catch (Exception e){
            return new Response("Error al querer guardar el viaje, intentelo más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response deleteViajebyId(int id) {
        try {
            viajeRepository.deleteById(id);
            Response eliminado = getViajeById(id);
            if (eliminado.getError() && eliminado.getObject() == null){
                return new Response("Eliminado correctamente", id, false);
            }else{
                return new Response("Problemas al eliminar el viaje con el id: " + id, null, true);
            }

        }catch (Exception e){
            return new Response("Hubo un error al querer eliminar el viaje intente más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response getViajeById(int id) {
        try{
            Optional<Viaje> viaje = viajeRepository.findById(id);
            if (viaje.isPresent()){
                return new Response("Ok", viaje.get(), false);
            }else {
                return new Response("No se encontro el viaje con el id: " + id, null, true);
            }
        }catch (Exception e){
            return new Response("Hubo un error al querer consultar el viaje intente más tarde o comuniquese con el administrador", null, true);
        }
    }
}

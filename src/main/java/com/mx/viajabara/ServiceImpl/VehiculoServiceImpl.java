package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Vehiculo;
import com.mx.viajabara.Repository.VehiculoRepository;
import com.mx.viajabara.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired

    VehiculoRepository vehiculoRepository;

    @Override
    public Response saveOrUpdateVehiculo(Vehiculo vehiculo) {
        try {
            Vehiculo vehiculoSaved = vehiculoRepository.save(vehiculo);
            if (vehiculoSaved != null){
                return new Response("Guardado correctamente", vehiculoSaved, false);
            }else {
                return new Response("Hubo problemas al guardar el vehiculo", null, true);
            }
        }catch (Exception e){
                return new Response("Hubo problemas al querer ejecutar el método para guardar el vehiculo, intentelo más tarde o comuniquese con el administrador", null, true);
        }

    }

    @Override
    public Response getAll() {
        try{
            List<Vehiculo> vehiculos = vehiculoRepository.findAll();
            if (vehiculos.isEmpty()){
                return new Response("No hay vehiculos listados", null, false);
            }else {
                return new Response("Ok", vehiculos, true);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al querer ejecutar el método para obtener los vehículos", null, true);
        }
    }

    @Override
    public Response getVehiculoById(Long id) {
        try {
            Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
            if (vehiculo.isPresent()){
                return new Response("Vehículo guardado exitosamente", vehiculo, false);
            }else {
                return new Response("Problemas al guardar el vehículo", vehiculo, true);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al querer ejecutar el método de guardar el vehículo, intentelo más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response deleteVehiculo(Long id) {
        try{
            if (getVehiculoById(id)!= null){
                vehiculoRepository.deleteById(id);
                Response eliminado = getVehiculoById(id);
                if (eliminado.getError() && eliminado.getObject() == null){
                    return new Response("Ok", id, false);
                }else {
                    return new Response("No se pudo eliminar el vehiculo con el id " + id, id, true);
                }
            }else{
                return new Response("El id: " + id +" no existe", id, true);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al ejecutar el método para eliminar el vehículo", null, true);
        }
    }
}

package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.ViajeDTO;
import com.mx.viajabara.Entity.*;
import com.mx.viajabara.Repository.ClienteRepository;
import com.mx.viajabara.Repository.ParadaRepository;
import com.mx.viajabara.Repository.ViajeRepository;
import com.mx.viajabara.Service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    ParadaServiceImpl paradaService;
    @Autowired
    ParadaRepository paradaRepository;
    @Autowired
    ClienteRepository clienteRepository;
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
            System.out.println(e);
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
                            .numAsientosDisponibles(viaje.getNum_asientos_disponibles())
                            .viajeIniciado(false)
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
            System.out.println(e);
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



    @Override
    public Response filter(Long llegada, Long subida, String fecha){
        try {
            List<Viaje> viajeList = viajeRepository.findAll();
            Optional<Parada> paradaLlegada = paradaRepository.findById(llegada);
            Optional<Parada> paradaSubida = paradaRepository.findById(subida);
            List<Viaje> viajes = new ArrayList<>();
            for (Viaje viaje: viajeList){
                Ruta rutaTmp = viaje.getRuta();
                if (rutaTmp.getParadas().contains(paradaLlegada.get()) &&
                        rutaTmp.getParadas().contains(paradaSubida.get())){
                    LocalDate fechaTmp = LocalDate.parse(fecha);
                    if (viaje.getFechaViaje().isAfter(fechaTmp) || viaje.getFechaViaje().isEqual(fechaTmp)){
                        viajes.add(viaje);
                    }
                }
            }
            if (viajes.isEmpty()){
                return new Response("No hay viajes con los filtros especificados", viajeList, false);
            }else {
                return new Response("Ok", viajes, false);
            }
        }catch (Exception e){
                return new Response("Hubo problemas al querer filtrar los viajes", null, true);
        }
    }

    @Override
    public Response iniciarViaje(int idViaje){
        try {
            Viaje viaje = viajeRepository.findById(idViaje).get();
            viaje.setViajeIniciado(true);
            Parada parada = viaje.getRuta().getParadas().get(0);
            Set<Boleto> boletos = viaje.getBoletos();
            List<Boleto> clientesSuben = new ArrayList<>();
            for (Boleto boleto:
                 boletos) {
               Parada paradaTmp = paradaRepository.findById(boleto.getSubida()).get();
               if (paradaTmp.equals(parada)){
                   Cliente cliente = boleto.getCliente();
                   cliente.setViajeActivo(true);
                   clienteRepository.save(cliente);
                   clientesSuben.add(boleto);
               }
            }
            viajeRepository.save(viaje);
            if (clientesSuben.isEmpty()){
                return new Response("Ningun usuario sube en esta parada", null, false);
            }else {
                return new Response("Ok", clientesSuben, false);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al iniciar el viaje", null, true);
        }
    }

    @Override
    public Response getPasajerosBajanSuben(int idViaje, Long idParada){
        try {
            Viaje viaje = viajeRepository.findById(idViaje).get();
            Parada parada = paradaRepository.findById(idParada).get();
            Set<Boleto> boletosViaje =  viaje.getBoletos();
            List<Cliente> clienteListBajan = new ArrayList<>();
            List<Cliente> clienteSuben = new ArrayList<>();
            Map<String, List<Cliente>> clientes= new HashMap<String, List<Cliente>>() ;
            for (Boleto boleto:
                 boletosViaje) {
                if (paradaRepository.findById(boleto.getBajada()).get().equals(parada)){
                    clienteListBajan.add(boleto.getCliente());
                }
                if (paradaRepository.findById(boleto.getSubida()).get().equals(parada)){
                    clienteSuben.add(boleto.getCliente());
                }
            }
            clientes.put("clientesBajan", clienteListBajan);
            clientes.put("clientesSuben", clienteSuben);
            return new Response("Ok", clientes, false);
        }catch (Exception e){
            return new Response("Hubo un error al obtener la lista de pasajeros", null, false);
        }
    }

    @Override
    public Response terminarViaje(int idViaje){
        try{
            Viaje viaje = viajeRepository.findById(idViaje).get();
            viaje.setViajeIniciado(false);
            Set<Boleto> boletos = viaje.getBoletos();
            for (Boleto boleto: boletos){
                Cliente cliente = boleto.getCliente();
                cliente.setViajeActivo(false);
                clienteRepository.save(cliente);
            }
            return new Response("Ok", viaje, false);
        }catch (Exception e){
            return new Response("Hubo problemas al terminar el viaje", null, true);
        }
    }
}

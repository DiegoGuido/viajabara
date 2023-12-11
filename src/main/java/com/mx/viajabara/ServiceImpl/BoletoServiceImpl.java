package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.ActualizarViajeDTO;
import com.mx.viajabara.Dto.BoletoDTO;
import com.mx.viajabara.Dto.ValidateBoletoDTO;
import com.mx.viajabara.Entity.*;
import com.mx.viajabara.Repository.*;
import com.mx.viajabara.Service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BoletoServiceImpl implements BoletoService {
    @Autowired
    BoletoRepository boletoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ViajeRepository viajeRepository;

    @Autowired
    ParadaRepository paradaRepository;

    @Override
    public Response getAll() {
        try{
           List<Boleto> boletos =  boletoRepository.findAll();
           if (boletos !=null ){
               return new Response("OK", boletos, false);
           }else{
               return new Response("No hay boletos disponibles", boletos, false);
           }
        }catch (Exception e){
            return new Response("Problemas al querer obtener los boletos", null, true);
        }
    }

    @Override
    public Response deleteById(int id) {
        try {
            boletoRepository.deleteById(id);
            Response eliminado = getById(id);
            if (eliminado.getError() && eliminado.getObject() == null) {
                return new Response("Se elimino correctamente", id, false);
            }else {
                return new Response("No se pudo eliminar el boleto con el id: " + id, id, true);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al querer eliminar el boleto, intentelo más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response saveBoleto(BoletoDTO boleto) {
        try {
            Boleto boletoEntity = Boleto.builder()
                    .idBoleto(boleto.getBoletoId())
                    .asiento(boleto.getAsiento())
                    .cliente(clienteRepository.findById(boleto.getClienteId()).get())
                    .viaje(viajeRepository.findById(boleto.getViajeId()).get())
                    .precio(boleto.getPrecio())
                    .subida(boleto.getSubida())
                    .bajada(boleto.getBajada())
                    .build();
           Boleto boletoSaved = boletoRepository.save(boletoEntity);
           if (boletoSaved != null){
               return new Response("Ok", boletoSaved, false);
           }else{
               return new Response("Hubo problemas al guardar el boleto", boletoSaved, true);
           }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            return new Response("Hubo problemas al querer ejecutar el método para guardar el boleto", null, true);
        }
    }

    @Override
    public Response getById(int id) {
        try{
            Optional<Boleto> boleto = boletoRepository.findById(id);
            if (boleto.isPresent()){
                return new Response("Ok", boleto, false);
            }else {
                return new Response("No se encontro el boleto con el siguiente id: " + id, id, true);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al querer ejecutar el método de obtener boleto", null, true);
        }
    }

    @Override
    public Response validateBoletos(ValidateBoletoDTO validateBoletoDTO){
        try{
            int idViaje = validateBoletoDTO.getIdViaje();
            Long paradaSubir = validateBoletoDTO.getBoletoList().get(0).getSubida();
            Long paradaBajar = validateBoletoDTO.getBoletoList().get(0).getBajada();
            int respuesta = cantidadAsientosDisponibles(paradaSubir, idViaje);
            System.out.println("Primer respuesta " + respuesta);
            if (respuesta>0){
                int cantidadBoletos = validateBoletoDTO.getBoletoList().size();
                if ((respuesta-cantidadBoletos)>=0){
                    int segundaValidacion = cantidadAsientosDisponibles(paradaSubir,idViaje, (respuesta-cantidadBoletos), paradaBajar);
                    if (segundaValidacion<0){
                        return new Response("No hay boletos disponibles", segundaValidacion, true);
                    }else{
                        return new Response("Si hay boletos disponibles", segundaValidacion, false);
                    }
                }
            }else {
                return new Response("No hay boletos disponibles", respuesta, true);
            }
            return new Response("Ok", respuesta, false);
        }catch (Exception e){
            System.out.println(e);
            return new Response("Ok", null, true);
        }
    }



    private int cantidadAsientosDisponibles(Long paradaSubir, int idViaje){
        Viaje viaje = viajeRepository.findById(idViaje).get();
        Parada paradaBreak = paradaRepository.findById(paradaSubir).get();
        List<Parada> ruta = viaje.getRuta().getParadas();
        int cantidadDisponible = viaje.getVehiculo().getNumAsientos();
        int indexParada = ruta.indexOf(paradaBreak);
        Set<Boleto> boletos = viaje.getBoletos();

        for (int i = 0; i <= indexParada; i++){
            Parada paradaTmp = ruta.get(i);
            for (Boleto boleto:
                 boletos) {
                if (paradaRepository.findById(boleto.getBajada()).get().equals(paradaTmp)){
                    cantidadDisponible++;
                }
                if (paradaRepository.findById(boleto.getSubida()).get().equals(paradaTmp)){
                    cantidadDisponible--;
                }
            }

        }

        return cantidadDisponible;
    }

    private int cantidadAsientosDisponibles(Long paradaSubir, int idViaje, int cantidadDisponible, Long paradaBajar){

        Viaje viaje = viajeRepository.findById(idViaje).get();
        List<Parada> ruta = viaje.getRuta().getParadas();
        Parada paradaSubirIndex = paradaRepository.findById(paradaSubir).get();
        Parada paradaBajadaIndex = paradaRepository.findById(paradaBajar).get();
        int indexParadaSubida = ruta.indexOf(paradaSubirIndex);
        int indexBajada =  ruta.indexOf(paradaBajadaIndex);
        Set<Boleto> boletos = viaje.getBoletos();
        Parada ultimaParada = ruta.get(ruta.size() -1);
        for (int i = indexParadaSubida +1; i<= indexBajada; i++){
            Parada paradaTmp = ruta.get(i);
            if (paradaRepository.findById(paradaBajar).get().equals(paradaTmp) && !paradaTmp.equals(ultimaParada)){
                cantidadDisponible++;
            }
            for (Boleto boleto:
                    boletos) {
                System.out.println("id boleto--->" + boleto.getIdBoleto());

                if (paradaRepository.findById(boleto.getBajada()).get().equals(paradaTmp) && !paradaTmp.equals(ultimaParada)){
                    cantidadDisponible++;
                }

                    if (paradaRepository.findById(boleto.getSubida()).get().equals(paradaTmp)) {
                        cantidadDisponible--;
                    }

            }
        }
        return cantidadDisponible;
    }

    @Override
    public Response getAsientosDisponibles(int idViaje, Long paradaSubir, Long paradaBajar){
        Viaje viaje = viajeRepository.findById(idViaje).get();
        Set<Boleto> boletos = viaje.getBoletos();
        List<Parada> listaParadas = viaje.getRuta().getParadas();
        Parada subirParada = paradaRepository.findById(paradaSubir).get();
        int indexParadaSubir = listaParadas.indexOf(subirParada);
        List<Integer> boletosOcupados = new ArrayList<>();

        for (Boleto boleto:
             boletos) {
            if (indexParadaSubir < listaParadas.indexOf(paradaRepository.findById(boleto.getBajada()).get())){
                boletosOcupados.add(boleto.getAsiento());
            }
        }

        return new Response("Ok", boletosOcupados, false);
    }
}

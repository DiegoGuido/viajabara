package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.BoletoDTO;
import com.mx.viajabara.Entity.Boleto;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Repository.*;
import com.mx.viajabara.Service.BoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
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
                    .subida(paradaRepository.getById(boleto.getSubida()))
                    .bajada(paradaRepository.getById(boleto.getBajada()))
                    .build();
           Boleto boletoSaved = boletoRepository.save(boletoEntity);
           if (boletoSaved != null){
               return new Response("Ok", boletoSaved, false);
           }else{
               return new Response("Hubo problemas al guardar el boleto", boletoSaved, true);
           }
        }catch (Exception e){
            return new Response("Hubo problemas al querer ejecutar el método para guardar el boleto", null, true);
        }
    }

    @Override
    public Response getById(int id) {
        return null;
    }
}

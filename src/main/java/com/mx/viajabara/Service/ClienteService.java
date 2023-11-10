package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Response;

import java.util.List;

public interface ClienteService {

    Response saveOrUpdateClient( ClienteDTO cliente);

    Response getAll();

    Response deleteCliente(Long id);

    boolean login(String email, String password);

    Response getClienteById(Long id);



}

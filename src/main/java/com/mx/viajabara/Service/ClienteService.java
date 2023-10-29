package com.mx.viajabara.Service;

import com.mx.viajabara.Entity.Cliente;

import java.util.List;

public interface ClienteService {

    boolean saveOrUpdateClient( Cliente cliente);

    List<Cliente> getAll();

    boolean deleteCliente(Long id);

    boolean login(String email, String password);

    Cliente getClienteById(Long id);



}

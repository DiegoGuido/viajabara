package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Entity.Cliente;

import java.util.List;

public interface ClienteService {

    boolean saveClient(ClienteDTO cliente) throws Exception;

    boolean updateClient(long id, ClienteDTO clienteDTO) throws Exception;

    List<Cliente> getAll() throws  Exception;

    boolean deleteCliente(Long id) throws Exception;

    Cliente getClienteById(Long id) throws Exception;

    Cliente login(String correo, String clave) throws Exception;



}

package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Dto.LoginDTO;
import com.mx.viajabara.Entity.Cliente;

import javax.naming.AuthenticationException;
import java.util.List;

public interface ClienteService {

    Cliente login(LoginDTO loginDTO) throws AuthenticationException;

    boolean saveClient(ClienteDTO cliente) throws Exception;

    boolean updateClient(long id, ClienteDTO clienteDTO) throws Exception;

    List<Cliente> getAll() throws  Exception;

    boolean deleteCliente(Long id) throws Exception;

    Cliente getClienteById(Long id) throws Exception;



}

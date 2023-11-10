package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ClienteDTO;
<<<<<<< HEAD
=======
import com.mx.viajabara.Dto.LoginDTO;
>>>>>>> feature/login-registro
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Response;

import javax.naming.AuthenticationException;
import java.util.List;

public interface ClienteService {

<<<<<<< HEAD
    Response saveOrUpdateClient( ClienteDTO cliente);

    Response getAll();

    Response deleteCliente(Long id);
=======
    Cliente login(LoginDTO loginDTO) throws AuthenticationException;

    boolean saveClient(ClienteDTO cliente) throws Exception;

    boolean updateClient(long id, ClienteDTO clienteDTO) throws Exception;
>>>>>>> feature/login-registro

    List<Cliente> getAll() throws  Exception;

<<<<<<< HEAD
    Response getClienteById(Long id);
=======
    boolean deleteCliente(Long id) throws Exception;

    Cliente getClienteById(Long id) throws Exception;
>>>>>>> feature/login-registro



}

package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Dto.LoginDTO;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Usuario;

import javax.naming.AuthenticationException;
import java.util.List;

public interface ClienteService {


    //Response saveOrUpdateClient( ClienteDTO cliente);
    Response getAll();
    Response deleteCliente(Long id);
    Response login(LoginDTO loginDTO) throws AuthenticationException;
    Response getClienteById(Long id);
    Response saveUser(ClienteDTO clienteDTO);
    Response getBoletosByCliente(Long idCliente);


}

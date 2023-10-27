package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Repository.ClienteRepository;
import com.mx.viajabara.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public boolean saveOrUpdateClient(Cliente cliente) {
        boolean response = false;

        Cliente clienteSave = clienteRepository.save(cliente);

        if (clienteSave != null) response = true;

        return response;
    }

    @Override
    public boolean login(String username, String password){
        boolean cliente = clienteRepository.existsByCorreoAndClave(username, password);
        return cliente;
    }

    @Override
    public List<Cliente> getAll() {
      List<Cliente> clientes = clienteRepository.findAll();
            return clientes;
    }

    @Override
    public boolean deleteCliente(Long id) {
        clienteRepository.deleteById(id);
        return getClienteById(id) == null;
    }

    @Override
    public boolean login(String username, String password){
        boolean cliente = clienteRepository.existsByCorreoAndClave(username, password);
        return cliente;
    }
    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
}

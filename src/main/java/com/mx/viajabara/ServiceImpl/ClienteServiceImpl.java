package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Repository.ClienteRepository;
import com.mx.viajabara.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    @Override
    public Cliente login(String correo, String clave) throws AuthenticationException {
        try {
            //Cliente currentCliente = clienteRepository.loginByCorreoAndClave(correo,clave);
            Optional<Cliente> currentCliente = clienteRepository.findById(1L);

            if (currentCliente != null){
                return currentCliente.get();
            }else {
                throw new AuthenticationException("Ocurrió un error de autenticación");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Ocurrió un error de autenticación");
        }
    }


    @Override
    public boolean saveClient(ClienteDTO cliente) throws Exception{
        try {
            Cliente clienteParsed = objectBuilderFromDTO(cliente);
            if (clienteParsed != null) {
                clienteRepository.save(clienteParsed);
                return true; // Insercion fue exitosa
            } else {
                return false; // No se pudo guardar el cliente, error de insercion en bd
            }

        } catch (Exception e) {
            throw new Exception("Ocurrió un error de conexión...");
        }

    }

    @Override
    public boolean updateClient(long id, ClienteDTO clienteDTO) throws Exception {
        Optional<Cliente> currentClient = clienteRepository.findById(id);
        try {
            if (currentClient.isPresent()) {
                Cliente clienteParsed = objectBuilderFromDTOWithID(clienteDTO, (int)id);
                clienteRepository.saveAndFlush(clienteParsed);
                return true;  // Update exitoso
            }else{
                return false;
            }
        } catch (Exception e) {
            throw new Exception("Ocurrió un error de conexión...");
        }
    }

    @Override
    public Cliente getClienteById(Long id) throws Exception{
        try {
            return clienteRepository.findById(id).orElse(null);
        } catch (Exception e) {
            // Manejar la excepción y lanzar una excepción personalizada
            throw new Exception("Ocurrió un error de conexión...");
        }
    }


    @Override
    public List<Cliente> getAll() throws Exception{
        try {
            List<Cliente> lista = clienteRepository.findAll();
            if (!lista.isEmpty()) {
                return lista; // Lista no está vacía, devuelve la lista
            } else {
                return new ArrayList<>(); // Lista vacía, devuelve una lista vacía en lugar de null
            }
        } catch (Exception e) {
            throw new Exception("Ocurrió un error de conexión...");
        }
    }

    @Override
    public boolean deleteCliente(Long id) throws Exception {
        try {
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);

            if (clienteOptional.isPresent()) { //Si existe, por lo tanto hay que eliminarlo
                clienteRepository.deleteById(id);
                return true; // Se eliminó exitosamente
            } else {
                return false; // El cliente no existía, no se eliminó
            }
        } catch (Exception e) {
            // Manejar la excepción y lanzar una excepción personalizada
            throw new Exception("Ocurrió un error de conexión...");
        }
    }

     static Cliente objectBuilderFromDTO(ClienteDTO clienteDTO){
        return Cliente.builder()
                .nombre(clienteDTO.getNombre())
                .correo(clienteDTO.getCorreo())
                .clave(clienteDTO.getClave())
                .fotoPerfil(clienteDTO.getFotoPerfil())
                .fechaNacimiento(clienteDTO.getFechaNacimiento())
                .boletos(clienteDTO.getBoletos()).build();
    }

    static Cliente objectBuilderFromDTOWithID(ClienteDTO clienteDTO, Integer id){
        return Cliente.builder()
                .idCliente(id)
                .nombre(clienteDTO.getNombre())
                .correo(clienteDTO.getCorreo())
                .clave(clienteDTO.getClave())
                .fotoPerfil(clienteDTO.getFotoPerfil())
                .fechaNacimiento(clienteDTO.getFechaNacimiento())
                .boletos(clienteDTO.getBoletos()).build();
    }


}

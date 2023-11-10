package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.ClienteDTO;
<<<<<<< HEAD
=======
import com.mx.viajabara.Dto.LoginDTO;
>>>>>>> feature/login-registro
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Response;
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
<<<<<<< HEAD
    public Response saveOrUpdateClient(ClienteDTO cliente) {
        try {

            Cliente clienteSave = Cliente.builder()
                    .idCliente(cliente.getIdCliente())
                    .nombre(cliente.getNombre())
                    .correo(cliente.getCorreo())
                    .clave(cliente.getClave())
                    .fechaNacimiento(cliente.getFechaNacimiento())
                    .fotoPerfil(cliente.getFotoPerfil())
                    .boletos(cliente.getBoletos()).build();

            Cliente saved = clienteRepository.save(clienteSave);

            if (saved != null){
                return new Response("Cuenta guardada con exito", saved, false);
            }else {
                return new Response("Problemas al guardar la información del cliente, intentelo más tarde o contacte al administrador",
                                    saved,
                                    true);
            }

        }catch (Exception e){
            System.out.println(e);
            return new Response("Problemas al ejecutar el método para guardar la información del cliente, intentelo más tarde o contacte al administrador",
                    null,
                    true);

        }
=======
    public Cliente login(LoginDTO loginDTO) throws AuthenticationException {
        try {
            Optional<Cliente> currentCliente = clienteRepository.findByCorreoAndClave(loginDTO.getCorreo(),loginDTO.getClave());

            System.out.println(currentCliente.get());

            if (currentCliente.isPresent()){
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

>>>>>>> feature/login-registro
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
<<<<<<< HEAD
    public Response getAll() {
        try{
            List<Cliente> clientesList= clienteRepository.findAll();
            if (clientesList == null){
                return new Response("No hay rutas disponibles", null, false);
            }else{
                return new Response("Ok", clientesList, false);
            }
        }catch (Exception e){
            return new Response("Hubo un error al querer consultar a los clientes intente más tarde o comuniquese con el administrador",
                                null,
                                true);
=======
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
>>>>>>> feature/login-registro
        }
    }

    @Override
<<<<<<< HEAD
    public Response deleteCliente(Long id) {
        try{
            clienteRepository.deleteById(id);
            Response eliminado = getClienteById(id);
            if (eliminado.getError() && eliminado.getObject() == null){
                return new Response("Eliminado correctamente", id, false);
            }else{
                return new Response("Problemas al eliminar la ruta con el id: " + id, id, true);
            }
        }catch (Exception e){
            return new Response("Hubo un error al querer eliminar la ruta intente más tarde o comuniquese con el administrador",
                                null,
                                true);
        }
    }

    @Override
    public Response getClienteById(Long id) {
        try{
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);
            if (clienteOptional.isPresent()){
                return new Response("Ok", clienteOptional.get(), false);
            }else {
                return new Response("No se encontro el cliente con el id" + id, null, true);
            }
        }catch (Exception e){
            return new Response("Hubo un error al querer consultar la ruta intente más tarde o comuniquese con el administrador",
                          null,
                           true);
        }
=======
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
>>>>>>> feature/login-registro
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

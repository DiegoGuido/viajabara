package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Dto.LoginDTO;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Role;
import com.mx.viajabara.Entity.Usuario;
import com.mx.viajabara.Repository.ClienteRepository;
import com.mx.viajabara.Repository.UsuarioRepository;
import com.mx.viajabara.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JwtServiceImpl jwtService;

    @Autowired
    AuthenticationManager authenticationManager;


    @Override
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

            if (saved != null) {
                return new Response("Cuenta guardada con exito", saved, false);
            } else {
                return new Response("Problemas al guardar la información del cliente, intentelo más tarde o contacte al administrador",
                        saved,
                        true);
            }

        } catch (Exception e) {
            System.out.println(e);
            return new Response("Problemas al ejecutar el método para guardar la información del cliente, intentelo más tarde o contacte al administrador",
                    null,
                    true);

        }
    }

    @Override
    public Response getAll() {
        try {
            List<Cliente> clientesList = clienteRepository.findAll();
            if (clientesList == null) {
                return new Response("No hay rutas disponibles", null, false);
            } else {
                return new Response("Ok", clientesList, false);
            }
        } catch (Exception e) {
            return new Response("Hubo un error al querer consultar a los clientes intente más tarde o comuniquese con el administrador",
                    null,
                    true);
        }
    }


    @Override
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
        try {
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);
            if (clienteOptional.isPresent()) {
                return new Response("Ok", clienteOptional.get(), false);
            } else {
                return new Response("No se encontro el cliente con el id" + id, null, true);
            }
        } catch (Exception e) {
            return new Response("Hubo un error al querer consultar la ruta intente más tarde o comuniquese con el administrador",
                    null,
                    true);
        }
    }

    @Override
    public Response saveUser(Usuario usuario) {
        Usuario usuarioSave = Usuario.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .correo(passwordEncoder.encode(usuario.getCorreo()))
                .clave(usuario.getClave())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .fotoPerfil(usuario.getFotoPerfil()).role(Role.USER)
                .build();
        usuarioRepository.save(usuarioSave);
        var jwtToken = jwtService.generateToken(usuarioSave);
        return new Response("Ok", jwtToken, false);
    }

    @Override
    public Cliente login(LoginDTO loginDTO) throws AuthenticationException {
        try {
            Optional<Cliente> currentCliente = clienteRepository.findByCorreoAndClave(loginDTO.getCorreo(),loginDTO.getClave());

            if (currentCliente.isPresent()){
                return currentCliente.get();
            }else {
                throw new AuthenticationException("Ocurrió un error de autenticación");
            }
        } catch (Exception e) {
            throw new AuthenticationException("Ocurrió un error de autenticación");
        }
    }

    public Response loginUser(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getCorreo(),
                        loginDTO.getCorreo()
                )
        );
        var user = usuarioRepository.findByCorreo(loginDTO.getCorreo()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new Response("Ok", jwtToken, false);
    }



}

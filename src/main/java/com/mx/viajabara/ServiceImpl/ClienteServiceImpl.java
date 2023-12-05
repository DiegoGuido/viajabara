package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.BoletoHistoryDTO;
import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Dto.LoginDTO;
import com.mx.viajabara.Entity.*;
import com.mx.viajabara.Repository.ClienteRepository;
import com.mx.viajabara.Repository.ConductorRepository;
import com.mx.viajabara.Repository.ParadaRepository;
import com.mx.viajabara.Repository.UsuarioRepository;
import com.mx.viajabara.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    ParadaRepository paradaRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ConductorRepository conductorRepository;
 /*   @Override
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
    }*/

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
                Cliente cliente = clienteOptional.get();
                cliente.setBoletos(null);
                return new Response("Ok", cliente, false);
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
    public Response saveUser(ClienteDTO usuario) {
        try {
            Usuario usuarioSave = Usuario.builder()
                    .idUsuario(usuario.getIdCliente())
                    .nombre(usuario.getNombre())
                    .correo(usuario.getCorreo())
                    .clave(passwordEncoder.encode(usuario.getClave()))
                    .fechaNacimiento(usuario.getFechaNacimiento())
                    .fotoPerfil(usuario.getFotoPerfil())
                    .numeroTelefono(usuario.getNumTelefono())
                    .role(Role.USER)
                    .build();

            Cliente cliente = Cliente.builder()
                    .idCliente(usuario.getIdCliente())
                    .boletos(usuario.getBoletos())
                    .usuario(usuarioSave).build();

            Cliente clienteSaved =  clienteRepository.save(cliente);
            Usuario usuarioSaved = usuarioRepository.save(usuarioSave);
            if (clienteSaved !=null && usuarioSaved != null){
                var jwtToken = jwtService.generateToken(usuarioSave);
                usuarioSaved.setToken(jwtToken);
                clienteSaved.setUsuario(usuarioSaved);
                return new Response("Ok", clienteSaved, false);
            }else{
                return new Response("Problemas al guardar el usuario", usuario, true);
            }
        }catch (Exception e){
            System.out.println(e);
            return new Response("Problemas al intentar guardar el usuario, intentelo más tarde o comuniquese con el administrador", null, true);
        }

    }

    /*
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
    }*/

    public Response login(LoginDTO loginDTO){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getCorreo(),
                            loginDTO.getClave()
                    )
            );
            var user = usuarioRepository.findByCorreo(loginDTO.getCorreo()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            user.setToken(jwtToken);
            if (user.getRole().equals(Role.USER)){
                Cliente cliente = clienteRepository.findByUsuario(user);
                cliente.setBoletos(null);
                cliente.setUsuario(user);
                return new Response("Ok", cliente, false);
            }else {
                Conductor conductor = conductorRepository.findByUsuario(user);
                conductor.setUsuario(user);
                return new Response("Ok", conductor, false);
            }


        }catch (BadCredentialsException e){
            return new Response("Credenciales incorrectas", null, true);
        }catch (Exception e){
            return new Response("Ocurrio un problema con el método de login, intentelo más tarde o comuniquese con el administrador", null, true);
        }
    }


    @Override
    public Response getBoletosByCliente(Long idCliente){
        try {
            Optional<Cliente> cliente = clienteRepository.findById(idCliente);
            if (cliente.isPresent()){
                Set<Boleto> boletosNormales = cliente.get().getBoletos();
                List<BoletoHistoryDTO> boletos = new ArrayList<>();
                for (Boleto boleto:
                        boletosNormales) {
                    BoletoHistoryDTO boletoHistoryDTO = BoletoHistoryDTO.builder()
                            .idBoleto(boleto.getIdBoleto())
                            .asiento(boleto.getAsiento())
                            .viaje(boleto.getViaje().getNombre())
                            .fecha(boleto.getViaje().getFechaViaje())
                            .precio(boleto.getPrecio())
                            .subida(paradaRepository.findById(boleto.getSubida()).get().getNombre())
                            .bajada(paradaRepository.findById(boleto.getBajada()).get().getNombre())
                            .nombreConductor(boleto.getViaje().getConductor().getUsuario().getNombre())
                            .modeloAuto(boleto.getViaje().getVehiculo().getModelo()).build();

                    boletos.add(boletoHistoryDTO);
                }
                return new Response("Ok", boletos, false);
            }else {
                return new Response("No se encontro al usuario", null, true);
            }
        }catch (Exception e){
            return new Response("Error al ejecutar el método para obtener los boletos del cliente", null, true);
        }
    }

}

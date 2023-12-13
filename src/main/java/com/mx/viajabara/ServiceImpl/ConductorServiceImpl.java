package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Dto.ConductorDTO;
import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Role;
import com.mx.viajabara.Entity.Usuario;
import com.mx.viajabara.Repository.ConductorRepository;
import com.mx.viajabara.Repository.UsuarioRepository;
import com.mx.viajabara.Service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    ConductorRepository conductorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JwtServiceImpl jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Response saveOrUpdateConductor(ConductorDTO usuario) {
        try{
            Usuario usuarioSave = Usuario.builder()
                    .idUsuario(usuario.getIdConductor())
                    .nombre(usuario.getNombre())
                    .correo(usuario.getCorreo())
                    .clave(passwordEncoder.encode(usuario.getClave()))
                    .fechaNacimiento(usuario.getFechaNacimiento())
                    .fotoPerfil(usuario.getFotoPerfil())
                    .role(Role.CONDUCTOR)
                    .numeroTelefono(usuario.getNumeroTelefono())
                    .build();

            Conductor conductor = Conductor.builder()
                    .idConductor(usuario.getIdConductor())
                    .rfc(usuario.getRfc())
                    .puntuacion(5)
                    .usuario(usuarioSave)
                    .build();

            Conductor conductorSaved = conductorRepository.save(conductor);
            Usuario usuarioSaved = usuarioRepository.save(usuarioSave);

            if (conductorSaved !=null && usuarioSaved != null){
                var jwtToken = jwtService.generateToken(usuarioSave);
                usuarioSaved.setToken(jwtToken);
                conductorSaved.setUsuario(usuarioSaved);
                return new Response("Ok", conductorSaved, false);
            }else{
                return new Response("Problemas al guardar el usuario", usuario, true);
            }

        }catch (Exception e){

            System.out.println(e);
                return new Response("Problemas al querer guardar un conductor", null, true);
        }
    }

    @Override
    public Response getAll() {
        try {
            List<Conductor> conductors = conductorRepository.findAll();
            if (conductors != null){
                return new Response("Ok", conductors, false);
            }else {
                return new Response("No hay conductores disponibles", conductors, false);
            }
        }catch (Exception e){
                return new Response("Hubo problemas al querer consultar los conductores intente más tarde o comuniquese con el administrador", null, true);
        }
    }

    @Override
    public Response deleteConductor(int id) {
        try {
            conductorRepository.deleteById(id);
            Response eliminado = getConductorById(id);
            if (eliminado.getError() && eliminado.getObject() == null) {
                return new Response("Se elimino correctamente", id, false);
            }else {
                return new Response("No se pudo eliminar al conductor con el id: " + id, id, true);
            }

        }catch (Exception e){
            return new Response("Hubo problemas al querer eliminar al conductor, intentelo más tarde o comuniquese con el administrador", null, true);
        }

    }

    @Override
    public Response getConductorById(int id){
        try {
            Optional<Conductor> conductor = conductorRepository.findById(id);
            if (conductor.isPresent()){
                return new Response("Ok", conductor.get(), false);
            }else {
                return new Response("No se encontro al conductor con el id: " + id, null, true);
            }
        }catch (Exception e){
            return new Response("Hubo problemas al querer buscar al conductor, intentelo más tarde o comuniquese con el administrador", null, true);
        }
    }
}

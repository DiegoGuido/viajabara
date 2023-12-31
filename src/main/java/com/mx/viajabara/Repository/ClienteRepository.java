package com.mx.viajabara.Repository;

import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Optional<Cliente> findByCorreoAndClave(String correo, String clave);

    Cliente findByUsuario(Usuario usuario);
}

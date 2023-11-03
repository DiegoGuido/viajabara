package com.mx.viajabara.Repository;

import com.mx.viajabara.Dto.ClienteDTO;
import com.mx.viajabara.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c.fechaNacimiento, c.correo, c.clave, c.nombre, c.fotoPerfil\n" +
            "FROM Cliente as c WHERE c.correo = :correo AND c.clave = :clave")
    Cliente loginByCorreoAndClave(@Param("correo") String correo, @Param("clave") String clave);

    Cliente findByCorreo(String correo);

}

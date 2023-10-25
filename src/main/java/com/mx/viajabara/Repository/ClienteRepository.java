package com.mx.viajabara.Repository;

import com.mx.viajabara.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCorreoAndClave(String correo, String clave);
}

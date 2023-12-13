package com.mx.viajabara.Repository;

import com.mx.viajabara.Entity.Administrador;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Administrador findByUsuario(Usuario usuario);
}

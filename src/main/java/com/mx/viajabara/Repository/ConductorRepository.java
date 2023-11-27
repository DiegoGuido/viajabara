package com.mx.viajabara.Repository;

import com.mx.viajabara.Entity.Conductor;
import com.mx.viajabara.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Integer> {

    Conductor findByUsuario(Usuario usuario);
}

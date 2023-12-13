package com.mx.viajabara.Repository;

import com.mx.viajabara.Entity.Boleto;
import com.mx.viajabara.Entity.Cliente;
import com.mx.viajabara.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Integer> {

    Boolean deleteAllByCliente(Cliente cliente);

}

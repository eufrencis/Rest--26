package com.projeto.trocaoleo.repository.cliente;

import com.projeto.trocaoleo.entity.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByIdAndAtivoTrue(Long id);
}

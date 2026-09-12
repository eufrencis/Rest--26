package com.projeto.trocaoleo.repository.ordemServico;

import com.projeto.trocaoleo.entity.ordemServico.OrdemServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity, Long> {

    //Optional é um container que representa um valor que pode ou nao estar presente evitando o erro nullpointer
    Optional<OrdemServicoEntity> findByIdAndAtivoTrue(Long id);

    List<OrdemServicoEntity> findAllByAtivoTrue();
}

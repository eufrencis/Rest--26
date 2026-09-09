package com.projeto.trocaoleo.repository.ordemServico;

import com.projeto.trocaoleo.entity.ordemServico.OrdemServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServicoEntity, Long> {
}

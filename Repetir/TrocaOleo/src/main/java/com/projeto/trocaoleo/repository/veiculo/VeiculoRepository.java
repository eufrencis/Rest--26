package com.projeto.trocaoleo.repository.veiculo;

import com.projeto.trocaoleo.entity.veiculo.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {
}

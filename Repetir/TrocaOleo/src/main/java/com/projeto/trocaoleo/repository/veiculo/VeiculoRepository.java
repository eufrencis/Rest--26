package com.projeto.trocaoleo.repository.veiculo;

import com.projeto.trocaoleo.entity.veiculo.VeiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, Long> {

    Optional<VeiculoEntity> findByIdAndAtivoTrue(Long id);

    List<VeiculoEntity> findAllByAtivoTrue();
}

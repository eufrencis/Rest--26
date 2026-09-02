package com.projeto.trocaoleo.mapper.veiculo;

import com.projeto.trocaoleo.dto.veiculo.VeiculoRequestDto;
import com.projeto.trocaoleo.dto.veiculo.VeiculoResponseDto;
import com.projeto.trocaoleo.entity.veiculo.VeiculoEntity;
import com.projeto.trocaoleo.mapper.cliente.ClienteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VeiculoMapper {

    private final ClienteMapper clienteMapper;

    public VeiculoEntity toEntity (VeiculoRequestDto veiculoRequestDto){
        return VeiculoEntity.builder()
                .marca(veiculoRequestDto.getMarca())
                .modelo(veiculoRequestDto.getModelo())
                .placa(veiculoRequestDto.getPlaca())
                .ano(veiculoRequestDto.getAno())
                .cor(veiculoRequestDto.getCor())
                .build();
    }

    public VeiculoResponseDto toResponse (VeiculoEntity entity){
        return VeiculoResponseDto.builder()
                .id(entity.getId())
                .marca(entity.getMarca())
                .modelo((entity.getModelo()))
                .placa(entity.getPlaca())
                .ano(entity.getAno())
                .cor(entity.getCor())
                .cliente(clienteMapper.toResponse(entity.getCliente()))
                .build();
    }
}

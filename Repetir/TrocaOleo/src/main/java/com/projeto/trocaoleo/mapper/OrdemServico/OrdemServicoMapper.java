package com.projeto.trocaoleo.mapper.OrdemServico;

import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoRequestDto;
import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoResponseDto;
import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoResumida;
import com.projeto.trocaoleo.entity.ordemServico.OrdemServicoEntity;
import com.projeto.trocaoleo.mapper.cliente.ClienteMapper;
import com.projeto.trocaoleo.mapper.veiculo.VeiculoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrdemServicoMapper {

    private final ClienteMapper mapper;
    private final VeiculoMapper veiculoMapper;

    public OrdemServicoEntity toEntity (OrdemServicoRequestDto requestDto){
        return  OrdemServicoEntity.builder()
                .numeroOsPapel(requestDto.getNumeroOsPapel())
                .data(requestDto.getData())
                .valorTotal(requestDto.getValorTotal())
                .desconto(requestDto.getDesconto())
                .km(requestDto.getKm())
                .formaPagamento(requestDto.getFormaPagamento())
                .observacao(requestDto.getObservacao())
                .tipoServico(requestDto.getTipoServico()).build();

    }

    public OrdemServicoResponseDto toResponse (OrdemServicoEntity entity){
        return OrdemServicoResponseDto.builder()
                .id(entity.getId())
                .numeroOsPapel(entity.getNumeroOsPapel())
                .data(entity.getData())
                .valorTotal(entity.getValorTotal())
                .desconto(entity.getDesconto())
                .valorFinalCalculado(entity.getValorFinalCalculado())
                .cliente(mapper.toResponse(entity.getCliente()))
                .veiculo(veiculoMapper.toResponse(entity.getVeiculo()))
                .km(entity.getKm())
                .formaPagamento(entity.getFormaPagamento())
                .observacao(entity.getObservacao())
                .tipoServico(entity.getTipoServico())
                .build();
    }

    public OrdemServicoResumida toEntityResumido (OrdemServicoEntity entity){
        return OrdemServicoResumida.builder()
                .id(entity.getId())
                .numeroOsPapel(entity.getNumeroOsPapel())
                .data(entity.getData())
                .valorFinalCalculado(entity.getValorFinalCalculado())
                .nameCliente(entity.getCliente().getName())
                .nomeVeiculo(entity.getVeiculo().getModelo())
                .placaVeiculo(entity.getVeiculo().getPlaca())
                .build();
    }
}

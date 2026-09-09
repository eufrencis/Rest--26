package com.projeto.trocaoleo.dto.ordemServico;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.trocaoleo.dto.cliente.ClienteResponseDto;
import com.projeto.trocaoleo.dto.veiculo.VeiculoResponseDto;
import com.projeto.trocaoleo.entity.cliente.ClienteEntity;
import com.projeto.trocaoleo.entity.veiculo.VeiculoEntity;
import com.projeto.trocaoleo.enums.TipoServico;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoResponseDto {

    private Long id;
    private Integer numeroOsPapel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private Double valorTotal;
    private Double desconto;
    private ClienteResponseDto cliente;
    private VeiculoResponseDto veiculo;
    private Double km;
    private String formaPagamento;
    private String observacao;
    private List<TipoServico> tipoServico;
}

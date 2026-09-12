package com.projeto.trocaoleo.dto.ordemServico;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoResumida {

    private Long id;
    private Integer numeroOsPapel;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private BigDecimal valorFinalCalculado;
    private String nameCliente;
    private String nomeVeiculo;
    private String placaVeiculo;

}

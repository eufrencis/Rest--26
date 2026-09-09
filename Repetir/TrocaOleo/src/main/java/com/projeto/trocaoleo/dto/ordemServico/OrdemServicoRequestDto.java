package com.projeto.trocaoleo.dto.ordemServico;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.trocaoleo.entity.cliente.ClienteEntity;
import com.projeto.trocaoleo.entity.veiculo.VeiculoEntity;
import com.projeto.trocaoleo.enums.TipoServico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoRequestDto {


    @NotNull
    private Integer numeroOsPapel;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @NotNull
    private Double valorTotal;

    private Double desconto;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long veiculoid;

    @NotNull
    private Double km;

    @NotBlank
    private String formaPagamento;

    private String observacao;

    @NotNull
    private List<TipoServico> tipoServico;
}

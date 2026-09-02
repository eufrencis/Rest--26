package com.projeto.trocaoleo.dto.veiculo;


import com.projeto.trocaoleo.dto.cliente.ClienteResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VeiculoResponseDto {


    private Long id;
    private String marca;
    private String modelo;
    private String placa;
    private String cor;
    private Integer ano;
    private ClienteResponseDto cliente;

}

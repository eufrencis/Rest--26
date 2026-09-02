package com.projeto.trocaoleo.dto.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ClienteResponseDto {

    private Long id;
    private String name;
    private String telefone;
    private String endereco;
    private String cpf;
}


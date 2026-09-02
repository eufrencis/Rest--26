package com.projeto.trocaoleo.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequestDto {

    @NotBlank (message = "O nome não pode estar em branco")
    private String name;

    @NotBlank (message = "O campo telefone não pode estar em branco")
    @Size(min = 10, max = 11, message = "O telefone precisa ter no mínimo 10 numeros e no máximo 11")
    private String telefone;

    private String endereco;

    @CPF(message = "O CPF é inválido")
    private String cpf;
}

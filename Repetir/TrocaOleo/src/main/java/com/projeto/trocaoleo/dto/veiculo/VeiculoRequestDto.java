package com.projeto.trocaoleo.dto.veiculo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoRequestDto {


    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    @Size(max = 7)
    private String placa;

    @NotBlank
    @Size(max = 15)
    private String cor;

    @NotNull
    @Min(value = 1900, message = "O ano do veículo é inválido")
    private Integer ano;

    @NotNull
    private Long clienteId;

}

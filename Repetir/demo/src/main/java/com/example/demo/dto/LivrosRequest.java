package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LivrosRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String autor;

    @NotNull
    private Double preco;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;
}

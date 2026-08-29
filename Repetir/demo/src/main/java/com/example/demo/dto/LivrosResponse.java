package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivrosResponse {

    private Long id;
    private String name;
    private String autor;
    private Double preco;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;

}

package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer priceInCents;
}

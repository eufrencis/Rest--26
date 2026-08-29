package com.example.demo.mapper;

import com.example.demo.dto.LivrosRequest;
import com.example.demo.dto.LivrosResponse;
import com.example.demo.entity.LivrosEntity;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class LivrosMapper {

    public LivrosEntity toEntity (LivrosRequest livrosRequest){
        return LivrosEntity.builder()
                .autor(livrosRequest.getAutor())
                .nome(livrosRequest.getNome())
                .preco(livrosRequest.getPreco())
                .dataPublicacao(livrosRequest.getDataPublicacao())
                .build();
    }

    public LivrosResponse toResponse (LivrosEntity entity){
        return LivrosResponse.builder()
                .id(entity.getId())
                .name(entity.getNome())
                .autor(entity.getAutor())
                .preco(entity.getPreco())
                .dataPublicacao(entity.getDataPublicacao())
                .build();
    }
}

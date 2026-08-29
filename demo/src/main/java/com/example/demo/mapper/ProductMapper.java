package com.example.demo.mapper;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toEntity (ProductRequest request){
        ProductEntity entity = ProductEntity.builder()
                .name(request.getName())
                .priceInCents(request.getPriceInCents())
                .build();
        return entity;

    }

    public ProductResponse toResponse (ProductEntity entity){
        ProductResponse response = ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .priceInCents(entity.getPriceInCents())
                .build();
        return response;
    }
}

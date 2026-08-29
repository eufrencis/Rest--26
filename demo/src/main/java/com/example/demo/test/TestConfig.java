package com.example.demo.test;

import com.example.demo.Repository.ProductRepository;
import com.example.demo.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run (String... args) throws Exception{

        ProductEntity p1 = ProductEntity.builder().
                name("Teclado Gamer")
                .priceInCents(1000)
                .build();

        ProductEntity p2 = ProductEntity.builder()
                .name("Notebook Asus")
                .priceInCents(5000)
                .build();
        productRepository.saveAll(List.of(p1, p2));

    }

}

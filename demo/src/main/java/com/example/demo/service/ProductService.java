package com.example.demo.service;

import com.example.demo.Repository.ProductRepository;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.entity.ProductEntity;
import com.example.demo.mapper.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    // o final diz que essa variavel pode receber um valor uma unica vez, dps disso ninguem pode troca-la por outro repositorio.
    // a palavra final obriga a variavel ser incializada no moomento que a classe é criada ms ela nao tem nada dentro
    //Por isso é necessario o construtor. O spring cria a instância productRepository e inteja no construtor

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @Transactional
    public ProductResponse save(ProductRequest product) {
        ProductEntity entity = productMapper.toEntity(product);

        ProductEntity savedEntity = productRepository.save(entity);

        return productMapper.toResponse(savedEntity);
    }

    public List<ProductResponse> getAllProducts() {
        List <ProductEntity> entities = productRepository.findAll();
        List <ProductResponse> responses = new ArrayList<>();

        for (ProductEntity linha : entities){
            ProductResponse response = productMapper.toResponse(linha);
            responses.add(response);
        }
        return responses;
    }

    public ProductResponse getProductById(Long id) {

        ProductEntity entity = findByAllIds(id);

        return productMapper.toResponse(entity);
    }

    public ProductResponse updateProduct(Long id, ProductRequest updateProduct) {

        ProductEntity entity = findByAllIds(id);

        entity.setName(updateProduct.getName());
        entity.setPriceInCents(updateProduct.getPriceInCents());

        ProductEntity entityUpdated = productRepository.save(entity);

        return productMapper.toResponse(entityUpdated);

    }

    public void delProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductResponse patchProduct(Long id, ProductRequest parcialProduct) {
        ProductEntity productFim = findByAllIds(id);

        if (parcialProduct.getName()!= null){
            productFim.setName(parcialProduct.getName());
        }
        if (parcialProduct.getPriceInCents() != null){
            if (parcialProduct.getPriceInCents() < 0 ){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preço invalido, favor insira um preço maior que zero");
            }
            productFim.setPriceInCents(parcialProduct.getPriceInCents());
        }

        ProductEntity savedEntity = productRepository.save(productFim);

        return productMapper.toResponse(savedEntity);

    }

    public ProductEntity findByAllIds (Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id nao encontrado"));
    }
}

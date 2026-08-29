package com.example.demo.Controller;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponse save (@Valid @RequestBody ProductRequest product){
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public ProductResponse getItemId (@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductResponse> getAllItens (){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductResponse putItens (@PathVariable Long id, @Valid @RequestBody ProductRequest product){
        return productService.updateProduct(id, product);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct (@PathVariable Long id){
        productService.delProduct(id);
    }

    @PatchMapping("/{id}")
    public ProductResponse pathProduct (@PathVariable Long id, @RequestBody ProductRequest product){
        return productService.patchProduct(id, product);

    }



}

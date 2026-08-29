package com.example.demo.controller;


import com.example.demo.dto.LivrosRequest;
import com.example.demo.dto.LivrosResponse;
import com.example.demo.service.LivrosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/livros")
@CrossOrigin("http://localhost:4200")
public class LivrosController {

    private final LivrosService service;

    @PostMapping
    public LivrosResponse save (@Valid @RequestBody LivrosRequest request){
       return service.save(request);
    }

    @GetMapping("/{id}")
    public LivrosResponse findById (@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public List<LivrosResponse> findAll (){
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public LivrosResponse putLivros (@PathVariable Long id, @Valid @RequestBody LivrosRequest livrosRequest){
        return service.putLivros(id, livrosRequest);
    }

    @PatchMapping("/{id}")
    public LivrosResponse patchLivros (@PathVariable Long id, @RequestBody LivrosRequest livrosRequest){
        return service.patchLivros(id, livrosRequest);

    }


}

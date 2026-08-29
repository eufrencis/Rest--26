package com.projeto.clientes.controller;

import com.projeto.clientes.dto.ClienteRequest;
import com.projeto.clientes.dto.ClienteResponse;
import com.projeto.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse save (@RequestBody @Valid ClienteRequest request){
        return service.save(request);
    }

    @GetMapping("/{id}")
    public ClienteResponse findById (@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public List<ClienteResponse> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ClienteResponse putCliente (@PathVariable Long id, @RequestBody @Valid ClienteRequest request){
        return service.putCliente(id, request);
    }

    @PatchMapping("/{id}")
    public ClienteResponse patchCliente (@PathVariable Long id, @RequestBody ClienteRequest request){
        return service.patchCliente(id, request);
    }







}

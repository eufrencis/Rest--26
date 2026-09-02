package com.projeto.trocaoleo.controler.cliente;

import com.projeto.trocaoleo.dto.cliente.ClienteRequestDto;
import com.projeto.trocaoleo.dto.cliente.ClienteResponseDto;
import com.projeto.trocaoleo.service.cliente.ClienteService;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ClienteResponseDto save (@Valid @RequestBody ClienteRequestDto requestDto){
        return service.save(requestDto);
    }

    @GetMapping
    public List<ClienteResponseDto> findAll (){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClienteResponseDto findById (@PathVariable Long id){
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public ClienteResponseDto putCliente (@PathVariable Long id, @Valid @RequestBody ClienteRequestDto requestDto){
        return service.putCliente(id, requestDto);
    }

    @PatchMapping("/{id}")
    public ClienteResponseDto patchCliente (@PathVariable Long id, @RequestBody ClienteRequestDto requestDto){
        return service.patchCliente(id, requestDto);
    }



}

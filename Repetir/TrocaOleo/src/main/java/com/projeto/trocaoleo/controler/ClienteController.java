package com.projeto.trocaoleo.controler;

import com.projeto.trocaoleo.dto.ClienteRequestDto;
import com.projeto.trocaoleo.dto.ClienteResponseDto;
import com.projeto.trocaoleo.service.ClienteService;
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



}

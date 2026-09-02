package com.projeto.trocaoleo.controler.veiculo;

import com.projeto.trocaoleo.dto.veiculo.VeiculoRequestDto;
import com.projeto.trocaoleo.dto.veiculo.VeiculoResponseDto;
import com.projeto.trocaoleo.service.veiculo.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculos")

public class VeiculoController {

    private final VeiculoService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public VeiculoResponseDto save (@Valid @RequestBody VeiculoRequestDto requestDto){
        return service.save(requestDto);
    }

    @GetMapping("/{id}")
    public VeiculoResponseDto findById (@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public List<VeiculoResponseDto> findAll (){
        return service.FindAll();
    }


}

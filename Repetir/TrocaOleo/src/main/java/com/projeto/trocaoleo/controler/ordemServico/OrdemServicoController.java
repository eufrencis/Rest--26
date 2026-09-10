package com.projeto.trocaoleo.controler.ordemServico;

import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoRequestDto;
import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoResponseDto;
import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoResumida;
import com.projeto.trocaoleo.service.ordemServico.OrdemServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordemservicos")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrdemServicoResponseDto responseDto (@Valid @RequestBody OrdemServicoRequestDto requestDto){
        return service.save(requestDto);
    }

    @GetMapping("/{id}")
    public OrdemServicoResponseDto findById (@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public List<OrdemServicoResumida> findAll (){
        return service.findAll();
    }

    @PutMapping("/{id}")
    public OrdemServicoResponseDto update (@Valid @RequestBody OrdemServicoRequestDto ordemServicoRequestDto){
        return service.update(ordemServicoRequestDto);
    }


}

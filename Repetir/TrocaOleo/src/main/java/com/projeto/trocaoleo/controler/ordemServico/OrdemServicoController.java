package com.projeto.trocaoleo.controler.ordemServico;

import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoRequestDto;
import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoResponseDto;
import com.projeto.trocaoleo.service.ordemServico.OrdemServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordemservicos")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService service;

    @PostMapping
    public OrdemServicoResponseDto responseDto (@Valid @RequestBody OrdemServicoRequestDto requestDto){
        return service.save(requestDto);
    }


}

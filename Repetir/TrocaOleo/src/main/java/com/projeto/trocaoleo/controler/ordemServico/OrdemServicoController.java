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
    public OrdemServicoResponseDto put (@PathVariable Long id, @Valid @RequestBody OrdemServicoRequestDto ordemServicoRequestDto){
        return service.put(id, ordemServicoRequestDto);
    }

    @PatchMapping("/{id}")
    public OrdemServicoResponseDto patch (@PathVariable Long id, @RequestBody OrdemServicoRequestDto requestDto){
        return service.patch(id,requestDto);
    }

    // Retorna HTTP 204 (No Content) para indicar exclusão bem-sucedida sem corpo na resposta,
    // economizando largura de banda e seguindo o padrão REST
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        service.delete(id);
    }


}

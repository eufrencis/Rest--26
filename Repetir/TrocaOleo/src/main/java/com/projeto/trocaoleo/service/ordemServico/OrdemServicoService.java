package com.projeto.trocaoleo.service.ordemServico;

import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoRequestDto;
import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoResponseDto;
import com.projeto.trocaoleo.entity.cliente.ClienteEntity;
import com.projeto.trocaoleo.entity.ordemServico.OrdemServicoEntity;
import com.projeto.trocaoleo.entity.veiculo.VeiculoEntity;
import com.projeto.trocaoleo.mapper.OrdemServico.OrdemServicoMapper;
import com.projeto.trocaoleo.repository.cliente.ClienteRepository;
import com.projeto.trocaoleo.repository.ordemServico.OrdemServicoRepository;
import com.projeto.trocaoleo.repository.veiculo.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class OrdemServicoService {

    private final OrdemServicoMapper mapper;
    private final OrdemServicoRepository repository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;


    public OrdemServicoResponseDto save (OrdemServicoRequestDto requestDto){
        ClienteEntity clienteEntity = clienteRepository.findById(requestDto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        VeiculoEntity veiculoEntity = veiculoRepository.findById(requestDto.getVeiculoid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        OrdemServicoEntity ordemServicoEntity = mapper.toEntity(requestDto);
        ordemServicoEntity.setVeiculo(veiculoEntity);
        ordemServicoEntity.setCliente(clienteEntity);

        OrdemServicoEntity ordemServicoEntitySalva = repository.save(ordemServicoEntity);
        return mapper.toResponse(ordemServicoEntitySalva);

    }
}

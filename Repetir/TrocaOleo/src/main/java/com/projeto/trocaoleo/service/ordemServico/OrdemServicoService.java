package com.projeto.trocaoleo.service.ordemServico;

import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoRequestDto;
import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoResponseDto;
import com.projeto.trocaoleo.dto.ordemServico.OrdemServicoResumida;
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

import java.util.ArrayList;
import java.util.List;

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

    public OrdemServicoResponseDto findById (Long id){
        OrdemServicoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException (HttpStatus.NOT_FOUND, "Id da ordem de serviço não existe"));
        return mapper.toResponse(entity);
    }

    public List<OrdemServicoResumida> findAll (){
        List<OrdemServicoEntity> entityList = repository.findAll();
        List<OrdemServicoResumida> ordemServicoResumidas = new ArrayList<>();

        for (OrdemServicoEntity entity : entityList){
            ordemServicoResumidas.add(mapper.toEntityResumido(entity));
        }
        return ordemServicoResumidas;
    }

    public OrdemServicoResponseDto update (OrdemServicoRequestDto requestDto){
        ClienteEntity clienteEntity = clienteRepository.findById(requestDto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        VeiculoEntity veiculoEntity = veiculoRepository.findById(requestDto.getVeiculoid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        OrdemServicoEntity ordemServicoEntity = mapper.toEntity(requestDto);

        ordemServicoEntity.setNumeroOsPapel(requestDto.getNumeroOsPapel());
        ordemServicoEntity.setData(requestDto.getData());
        ordemServicoEntity.setValorTotal(requestDto.getValorTotal());
        ordemServicoEntity.setDesconto(requestDto.getDesconto());
        ordemServicoEntity.setCliente(clienteEntity);
        ordemServicoEntity.setVeiculo(veiculoEntity);
        ordemServicoEntity.setKm(requestDto.getKm());
        ordemServicoEntity.setFormaPagamento(requestDto.getFormaPagamento());
        ordemServicoEntity.setObservacao(requestDto.getObservacao());
        ordemServicoEntity.setTipoServico(requestDto.getTipoServico());

        OrdemServicoEntity ordemServicoEntitySalva = repository.save(ordemServicoEntity);

        return mapper.toResponse(ordemServicoEntitySalva);

    }


}

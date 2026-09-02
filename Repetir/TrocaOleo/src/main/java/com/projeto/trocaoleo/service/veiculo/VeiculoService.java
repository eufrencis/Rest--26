package com.projeto.trocaoleo.service.veiculo;

import com.projeto.trocaoleo.dto.veiculo.VeiculoRequestDto;
import com.projeto.trocaoleo.dto.veiculo.VeiculoResponseDto;
import com.projeto.trocaoleo.entity.cliente.ClienteEntity;
import com.projeto.trocaoleo.entity.veiculo.VeiculoEntity;
import com.projeto.trocaoleo.mapper.veiculo.VeiculoMapper;
import com.projeto.trocaoleo.repository.cliente.ClienteRepository;
import com.projeto.trocaoleo.repository.veiculo.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoMapper mapper;
    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;


    public VeiculoResponseDto save (VeiculoRequestDto veiculoRequestDto){
        ClienteEntity clienteEntity = clienteRepository.findById(veiculoRequestDto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao encontrado"));
        VeiculoEntity veiculoEntity = mapper.toEntity(veiculoRequestDto);
        veiculoEntity.setCliente(clienteEntity);
        VeiculoEntity veiculoEntitySave = veiculoRepository.save(veiculoEntity);
        return mapper.toResponse(veiculoEntitySave);
    }

    public VeiculoResponseDto findById (Long id){
        VeiculoEntity entity = veiculoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapper.toResponse(entity);
    }

    public List<VeiculoResponseDto> FindAll (){
        List<VeiculoEntity> veiculoEntities = veiculoRepository.findAll();
        List<VeiculoResponseDto> veiculoResponseDtos = new ArrayList<>();

        for (VeiculoEntity entity: veiculoEntities){
            veiculoResponseDtos.add(mapper.toResponse(entity));
        }
        return veiculoResponseDtos;
    }




}

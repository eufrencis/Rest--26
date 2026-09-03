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
        ClienteEntity clienteEntity = clienteRepository.findById(veiculoRequestDto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        VeiculoEntity veiculoEntity = mapper.toEntity(veiculoRequestDto);
        veiculoEntity.setCliente(clienteEntity);
        VeiculoEntity veiculoSalvo = veiculoRepository.save(veiculoEntity);
        return mapper.toResponse(veiculoSalvo);

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

    public VeiculoResponseDto putVeiculo (Long id, VeiculoRequestDto veiculoRequestDto){

        VeiculoEntity entity = veiculoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo não encontrado"));
        ClienteEntity clienteEntity = clienteRepository.findById(veiculoRequestDto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        entity.setMarca(veiculoRequestDto.getMarca());
        entity.setModelo(veiculoRequestDto.getModelo());
        entity.setCor(veiculoRequestDto.getCor());
        entity.setPlaca(veiculoRequestDto.getPlaca());
        entity.setAno(veiculoRequestDto.getAno());
        entity.setCliente(clienteEntity);

        VeiculoEntity entitySalva = veiculoRepository.save(entity);
        return mapper.toResponse(entitySalva);

    }

    public VeiculoResponseDto patchVeiculo (Long id, VeiculoRequestDto veiculoRequestDto){

        VeiculoEntity veiculoEntity = veiculoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (veiculoRequestDto.getModelo() != null){
            veiculoEntity.setModelo(veiculoRequestDto.getModelo());
        }

        if (veiculoRequestDto.getMarca() != null){
            veiculoEntity.setMarca(veiculoRequestDto.getMarca());
        }

        if (veiculoRequestDto.getCor() != null){
            veiculoEntity.setCor(veiculoRequestDto.getCor());
        }

        if (veiculoRequestDto.getPlaca() != null){
            veiculoEntity.setPlaca(veiculoRequestDto.getPlaca());
        }

        if (veiculoRequestDto.getAno() != null){
            veiculoEntity.setAno(veiculoRequestDto.getAno());
        }

        if (veiculoRequestDto.getClienteId() != null){
            ClienteEntity entity = clienteRepository.findById(veiculoRequestDto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            veiculoEntity.setCliente(entity);
        }

        VeiculoEntity veiculoSalvo = veiculoRepository.save(veiculoEntity);

        return mapper.toResponse(veiculoSalvo);
    }

    public void delete (Long id){
        VeiculoEntity entity = veiculoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        veiculoRepository.deleteById(entity.getId());
    }








}

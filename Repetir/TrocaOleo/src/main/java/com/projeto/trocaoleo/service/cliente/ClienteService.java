package com.projeto.trocaoleo.service.cliente;

import com.projeto.trocaoleo.dto.cliente.ClienteRequestDto;
import com.projeto.trocaoleo.dto.cliente.ClienteResponseDto;
import com.projeto.trocaoleo.entity.cliente.ClienteEntity;
import com.projeto.trocaoleo.mapper.cliente.ClienteMapper;
import com.projeto.trocaoleo.repository.cliente.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteResponseDto save (ClienteRequestDto request){
        ClienteEntity entity = clienteMapper.toEntity(request);
        ClienteEntity entitysave = clienteRepository.save(entity);
        return clienteMapper.toResponse(entitysave);
    }

    public List<ClienteResponseDto> findAll (){
        List<ClienteEntity> entities = clienteRepository.findAll();
        List<ClienteResponseDto> clienteResponseDtos = new ArrayList<>();

        for (ClienteEntity entity: entities){
             ClienteResponseDto responseDto = clienteMapper.toResponse(entity);
             clienteResponseDtos.add(responseDto);
        }
        return clienteResponseDtos;
    }

    public ClienteResponseDto findById (Long id){
        ClienteEntity entity = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return clienteMapper.toResponse(entity);
    }

    public ClienteResponseDto putCliente (Long id, ClienteRequestDto requestDto){
        ClienteEntity entity = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        entity.setName(requestDto.getName());
        entity.setCpf(requestDto.getCpf());
        entity.setEndereco(requestDto.getEndereco());
        entity.setTelefone(requestDto.getTelefone());

        ClienteEntity entitySalva = clienteRepository.save(entity);

        return clienteMapper.toResponse(entitySalva);
    }

    public ClienteResponseDto patchCliente (Long id, ClienteRequestDto requestDto){
        ClienteEntity entity = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


        if (requestDto.getName() != null){
            entity.setName(requestDto.getName());
        }

        if (requestDto.getCpf() != null){
            entity.setCpf(requestDto.getCpf());
        }

        if (requestDto.getEndereco() != null){
            entity.setEndereco(requestDto.getEndereco());
        }

        if (requestDto.getTelefone() != null){
            entity.setEndereco(requestDto.getEndereco());
        }

        ClienteEntity entitySalva = clienteRepository.save(entity);

        return clienteMapper.toResponse(entitySalva);
    }


}

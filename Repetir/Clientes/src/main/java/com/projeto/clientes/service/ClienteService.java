package com.projeto.clientes.service;

import com.projeto.clientes.dto.ClienteRequest;
import com.projeto.clientes.dto.ClienteResponse;
import com.projeto.clientes.entity.ClienteEntity;
import com.projeto.clientes.mapper.ClienteMapper;
import com.projeto.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteResponse save (ClienteRequest clienteRequest){
        ClienteEntity entity = clienteRepository.save(clienteMapper.toEntity(clienteRequest));
        return clienteMapper.toResponse(entity);
    }

    public ClienteResponse findById (Long id){
        ClienteEntity entity =findEntityById(id);
        return clienteMapper.toResponse(entity);
    }

    public List<ClienteResponse> findAll (){
        List<ClienteEntity> entities = clienteRepository.findAll();
        List<ClienteResponse> responses = new ArrayList<>();

        for (ClienteEntity entity : entities){
            ClienteResponse response = clienteMapper.toResponse(entity);
            responses.add(response);
        }
        return responses;
    }

    public void delete (Long id){
        clienteRepository.delete(findEntityById(id));
    }

    public ClienteResponse putCliente (Long id, ClienteRequest request){
        ClienteEntity entity = findEntityById(id);

        entity.setName(request.getName());
        entity.setCpf(request.getCpf());
        entity.setDataNascimento(request.getDataNascimento());

        ClienteEntity entitySalva = clienteRepository.save(entity);

        return clienteMapper.toResponse(entitySalva);
    }

    public ClienteResponse patchCliente (Long id, ClienteRequest request){
        ClienteEntity entity = findEntityById(id);

        if (request.getName() != null){
            entity.setName(request.getName());
        }

        if (request.getDataNascimento() != null){
            entity.setDataNascimento(request.getDataNascimento());
        }

        if (request.getCpf() != null){
            entity.setCpf(request.getCpf());
        }

        ClienteEntity entitySalva = clienteRepository.save(entity);

        return clienteMapper.toResponse(entitySalva);
    }



    private ClienteEntity findEntityById (Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

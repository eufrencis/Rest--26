package com.projeto.clientes.mapper;

import com.projeto.clientes.dto.ClienteRequest;
import com.projeto.clientes.dto.ClienteResponse;
import com.projeto.clientes.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteEntity toEntity (ClienteRequest request){
        return ClienteEntity.builder()
                .name(request.getName())
                .cpf(request.getCpf())
                .dataNascimento(request.getDataNascimento())
                .build();
    }



public ClienteResponse toResponse (ClienteEntity entity) {
    return ClienteResponse.builder()
            .id(entity.getId())
            .name(entity.getName())
            .cpf(entity.getCpf())
            .dataNascimento(entity.getDataNascimento())
            .build();
    }
}

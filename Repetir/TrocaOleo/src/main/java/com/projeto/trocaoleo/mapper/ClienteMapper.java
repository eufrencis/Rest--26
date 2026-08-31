package com.projeto.trocaoleo.mapper;


import com.projeto.trocaoleo.dto.ClienteRequestDto;
import com.projeto.trocaoleo.dto.ClienteResponseDto;
import com.projeto.trocaoleo.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteEntity toEntity (ClienteRequestDto clienteRequestDto){
        return ClienteEntity.builder()
                .name(clienteRequestDto.getName())
                .cpf(clienteRequestDto.getCpf())
                .telefone(clienteRequestDto.getTelefone())
                .endereco(clienteRequestDto.getEndereco())
                .build();
    }

    public ClienteResponseDto toResponse (ClienteEntity entity){
        return ClienteResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .telefone(entity.getTelefone())
                .endereco((entity.getEndereco()))
                .build();
    }
}

package com.example.demo.service;

import com.example.demo.dto.LivrosRequest;
import com.example.demo.dto.LivrosResponse;
import com.example.demo.entity.LivrosEntity;
import com.example.demo.mapper.LivrosMapper;
import com.example.demo.repository.LivrosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivrosService {

    private final LivrosRepository livrosRepository;
    private final LivrosMapper livrosMapper;


    public LivrosResponse save(LivrosRequest request){

        LivrosEntity entity = livrosMapper.toEntity(request);
        LivrosEntity entitySalva = livrosRepository.save(entity);
        return livrosMapper.toResponse(entitySalva);
    }

    public LivrosResponse findById(Long id){
        LivrosEntity entity = findEntityById(id);
        return livrosMapper.toResponse(entity);
    }

    public List <LivrosResponse> findAll (){
        List<LivrosEntity> livrosEntityList = livrosRepository.findAll();
        List <LivrosResponse> livrosResponseList = new ArrayList<>();

        for(LivrosEntity obj : livrosEntityList){
            livrosResponseList.add(livrosMapper.toResponse(obj));
        }
        return livrosResponseList;
    }

    public LivrosResponse putLivros (Long id, LivrosRequest livrosRequest){
        LivrosEntity entity = findEntityById(id);

        entity.setNome(livrosRequest.getNome());
        entity.setAutor(livrosRequest.getAutor());
        entity.setPreco(livrosRequest.getPreco());
        entity.setDataPublicacao(livrosRequest.getDataPublicacao());

        LivrosEntity entitySalva = livrosRepository.save(entity);

        return livrosMapper.toResponse(entitySalva);

    }

    public void delete (Long id){
        if(!livrosRepository.existsById(id)){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado");
        }
        livrosRepository.deleteById(id);
    }

    public LivrosResponse patchLivros (Long id, LivrosRequest livrosRequest){
        LivrosEntity entity = findEntityById(id);

        if (livrosRequest.getNome() != null){
            entity.setNome(livrosRequest.getNome());
        }
        if (livrosRequest.getAutor() != null){
            entity.setAutor(livrosRequest.getAutor());
        }
        if (livrosRequest.getPreco() != null){
            entity.setPreco(livrosRequest.getPreco());
            }
        if (livrosRequest.getDataPublicacao() != null){
            entity.setDataPublicacao(livrosRequest.getDataPublicacao());
        }

        LivrosEntity entitySalva = livrosRepository.save(entity);

        return livrosMapper.toResponse(entitySalva);
    }

    public LivrosEntity findEntityById (Long id){
        return livrosRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado"));
    }

}

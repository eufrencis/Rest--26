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
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional //Garante que se algo falhar na criação nada fica salvo pela metade
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
        OrdemServicoEntity entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new ResponseStatusException (HttpStatus.NOT_FOUND, "Id da ordem de serviço não existe"));
        return mapper.toResponse(entity);
    }

    public List<OrdemServicoResumida> findAll (){
        List<OrdemServicoEntity> entityList = repository.findAllByAtivoTrue();
        List<OrdemServicoResumida> ordemServicoResumidas = new ArrayList<>();

        for (OrdemServicoEntity entity : entityList){
            ordemServicoResumidas.add(mapper.toEntityResumido(entity));
        }
        return ordemServicoResumidas;
    }

    @Transactional// Mantém a conexão aberta para o Hibernate executar o .clear() e .addAll()
    // da coleção sem lançar exceções de lazy loading, além de garantir o rollback se a busca por Cliente ou Veículo falhar.
    public OrdemServicoResponseDto put (Long id, OrdemServicoRequestDto requestDto){
        OrdemServicoEntity ordemServicoEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ordem de servico não encontrada"));
        ClienteEntity clienteEntity = clienteRepository.findById(requestDto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        VeiculoEntity veiculoEntity = veiculoRepository.findById(requestDto.getVeiculoid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        ordemServicoEntity.setNumeroOsPapel(requestDto.getNumeroOsPapel());
        ordemServicoEntity.setData(requestDto.getData());
        ordemServicoEntity.setValorTotal(requestDto.getValorTotal());
        ordemServicoEntity.setDesconto(requestDto.getDesconto());
        ordemServicoEntity.setCliente(clienteEntity);
        ordemServicoEntity.setVeiculo(veiculoEntity);
        ordemServicoEntity.setKm(requestDto.getKm());
        ordemServicoEntity.setFormaPagamento(requestDto.getFormaPagamento());
        ordemServicoEntity.setObservacao(requestDto.getObservacao());

        ordemServicoEntity.getTipoServico().clear();
        if(requestDto.getTipoServico() != null){
            ordemServicoEntity.getTipoServico().addAll(requestDto.getTipoServico());
        }
        OrdemServicoEntity ordemServicoEntitySalva = repository.save(ordemServicoEntity);
        return mapper.toResponse(ordemServicoEntitySalva);
    }

    @Transactional //Garante a integridade transacional ao alterar pontualmente só alguns campos ou coleções da entidade.
    public OrdemServicoResponseDto patch (Long id, OrdemServicoRequestDto requestDto){
        OrdemServicoEntity ordemServicoEntity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (requestDto.getNumeroOsPapel() != null){
            ordemServicoEntity.setNumeroOsPapel(requestDto.getNumeroOsPapel());
        }
        if (requestDto.getData() != null){
            ordemServicoEntity.setData(requestDto.getData());
        }
        if (requestDto.getValorTotal() != null){
            ordemServicoEntity.setValorTotal(requestDto.getValorTotal());
        }
        if (requestDto.getDesconto() != null){
            ordemServicoEntity.setDesconto(requestDto.getDesconto());
        }
        if (requestDto.getClienteId() != null){
            ClienteEntity clienteEntity = clienteRepository.findById(requestDto.getClienteId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            ordemServicoEntity.setCliente(clienteEntity);
        }
        if (requestDto.getVeiculoid() != null){
            VeiculoEntity veiculoEntity = veiculoRepository.findById(requestDto.getVeiculoid()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            ordemServicoEntity.setVeiculo(veiculoEntity);
        }
        if (requestDto.getKm() != null){
            ordemServicoEntity.setKm(requestDto.getKm());
        }
        if (requestDto.getFormaPagamento() != null){
            ordemServicoEntity.setFormaPagamento(requestDto.getFormaPagamento());
        }
        if (requestDto.getObservacao() != null){
            ordemServicoEntity.setObservacao(requestDto.getObservacao());
        }
        if (requestDto.getTipoServico() !=null && !requestDto.getTipoServico().isEmpty()){
            ordemServicoEntity.getTipoServico().clear();
            ordemServicoEntity.getTipoServico().addAll(requestDto.getTipoServico());
        }

        OrdemServicoEntity ordemServicoEntitySalva = repository.save(ordemServicoEntity);

        return mapper.toResponse(ordemServicoEntitySalva);
    }

    public void delete (Long id){
        OrdemServicoEntity entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setAtivo(false);
        repository.save(entity);
    }






}

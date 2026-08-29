package com.projeto.clientes.test;

import com.projeto.clientes.entity.ClienteEntity;
import com.projeto.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class ClienteTest implements CommandLineRunner {

    private final ClienteRepository clienteRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void run(String... args) throws Exception {

        ClienteEntity entity = ClienteEntity.builder()
                .name("francis ferreira")
                .cpf("06311453118")
                .dataNascimento(LocalDate.parse("28/05/1996", dtf))
                .build();

        clienteRepository.save(entity);

        ClienteEntity entity2 = ClienteEntity.builder()
                .name("Lais ")
                .cpf("06311453114")
                .dataNascimento(LocalDate.parse("28/05/1998", dtf))
                .build();

        clienteRepository.save(entity2);


    }
}

package com.projeto.trocaoleo.entity.cliente;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 11)
    private String telefone;

    @Column(nullable = true)
    private String endereco;

    @Column(unique = true, nullable = true, length = 14)
    private String cpf;
}

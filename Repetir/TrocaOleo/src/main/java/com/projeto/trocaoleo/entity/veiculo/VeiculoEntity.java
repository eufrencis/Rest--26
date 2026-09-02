package com.projeto.trocaoleo.entity.veiculo;

import com.projeto.trocaoleo.entity.cliente.ClienteEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "tb_veiculo")
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 15)
    private String marca;

    @Column(nullable = false, length = 15)
    private String modelo;

    @Column(nullable = false, length = 10)
    private String placa;

    @Column(nullable = false, length = 8)
    private String cor;

    @Column(nullable = false, length = 4)
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

}

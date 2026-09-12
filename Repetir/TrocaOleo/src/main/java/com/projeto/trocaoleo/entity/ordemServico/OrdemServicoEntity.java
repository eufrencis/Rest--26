package com.projeto.trocaoleo.entity.ordemServico;

import com.projeto.trocaoleo.entity.cliente.ClienteEntity;
import com.projeto.trocaoleo.entity.veiculo.VeiculoEntity;
import com.projeto.trocaoleo.enums.TipoServico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderm_servico")
public class OrdemServicoEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numeroOsPapel;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private Double valorTotal;

    private Double desconto;

    @OneToOne @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @OneToOne @JoinColumn(name = "veiculo_id", nullable = false)
    private VeiculoEntity veiculo;

    @Column(nullable = false)
    private Double km;

    @Column(nullable = false)
    private String formaPagamento;

    private String observacao;

    // Indica que é uma coleção de elementos simples (Enum) e cria a tabela auxiliar automaticamente
    @ElementCollection(targetClass = TipoServico.class)

// Salva a constante em formato texto (ex: 'TROCA_OLEO_MOTOR') em vez de salvar o índice numérico (0, 1, 2)
    @Enumerated(EnumType.STRING)

// Define o nome da tabela auxiliar e a coluna da chave estrangeira (FK) que aponta para esta ordem de serviço
    @CollectionTable(name = "ordem_servico_tipos", joinColumns = @JoinColumn(name = "ordem_servico_id"))

// Customiza a coluna da tabela auxiliar onde o valor do Enum será gravado e impede valores nulos
    @Column(name = "tipo_servico", nullable = false)
    private List<TipoServico> tipoServico;

    @Builder.Default
    @Column(nullable = false)
    private Boolean ativo = true;
}

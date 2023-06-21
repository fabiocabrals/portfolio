package com.spring.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "projeto_generator")
    @SequenceGenerator(name = "projeto_generator", sequenceName = "projeto_id_seq", allocationSize = 1)
    private Long id;
    private String nome;
    @Column(name = "data_inicio")
    private LocalDate dataInicio;
    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;
    @Column(name = "data_fim")
    private LocalDate dataFim;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private ProjetoStatus status;
    private BigDecimal orcamento;
    @Enumerated(EnumType.STRING)
    private ProjetoRisco risco;
    @JoinColumn(name="idgerente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Pessoa gerente;
    @JoinTable(name = "membros", joinColumns = {@JoinColumn(name = "idprojeto")}, inverseJoinColumns = {@JoinColumn(name = "idpessoa")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pessoa> membros;
}
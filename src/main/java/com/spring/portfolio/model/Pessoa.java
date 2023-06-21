package com.spring.portfolio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_generator")
    @SequenceGenerator(name = "pessoa_generator", sequenceName = "pessoa_id_seq", allocationSize = 1)
    private Long id;
    private String nome;
    @Column(name = "datanascimento")
    private LocalDate dataNascimento;
    private String cpf;
    private boolean funcionario;

}

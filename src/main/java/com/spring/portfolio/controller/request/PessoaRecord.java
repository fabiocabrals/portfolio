package com.spring.portfolio.controller.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record PessoaRecord(
        @NotBlank String nome,
        LocalDate dataNascimento,
        @CPF String cpf,
        boolean funcionario
) {
}

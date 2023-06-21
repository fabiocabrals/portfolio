package com.spring.portfolio.controller.request;

import com.spring.portfolio.model.Pessoa;
import com.spring.portfolio.model.ProjetoRisco;
import com.spring.portfolio.model.ProjetoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjetoRecord(
        @NotBlank String nome,
        @NotNull LocalDate dataInicio,
        @NotNull LocalDate dataPrevisaoFim,
        @NotNull LocalDate dataFim,
        @NotBlank String descricao,
        @NotNull ProjetoStatus status,
        @NotNull ProjetoRisco risco,
        @NotNull BigDecimal orcamento,
        @NotNull Pessoa gerente
) {
}

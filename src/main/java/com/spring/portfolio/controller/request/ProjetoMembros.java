package com.spring.portfolio.controller.request;

import com.spring.portfolio.model.Pessoa;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProjetoMembros(@NotNull List<Pessoa> membros) {}

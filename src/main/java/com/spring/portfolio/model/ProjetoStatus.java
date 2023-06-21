package com.spring.portfolio.model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public enum ProjetoStatus implements Serializable {
    EM_ANALISE("Em análise"),
    ANALISE_REALIZADA("Análise realizada"),
    ANALISE_APROVADA("Análise aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private String descricao;

    private ProjetoStatus(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    private static Map<ProjetoStatus, String> toMap() {
        return Arrays.asList(ProjetoStatus.values())
                .stream()
                .collect(Collectors.toMap(status -> status, status -> status.getDescricao()));
    }
    public static List<StatusRecord> toList() {
        return toMap().entrySet().
                stream().
                map(StatusRecord::new)
                .toList();
    }
    public record StatusRecord(String status, String descricao) {
        public StatusRecord(Map.Entry<ProjetoStatus, String> entry) {
            this(entry.getKey().toString(), entry.getValue());
        }
    }

}


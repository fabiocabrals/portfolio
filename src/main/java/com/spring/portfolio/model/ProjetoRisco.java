package com.spring.portfolio.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum ProjetoRisco {
    BAIXO("Baixo risco"),
    MEDIO("Médio risco"),
    ALTO("Alto risco");

    private String descricao;

    private ProjetoRisco(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    private static Map<ProjetoRisco, String> toMap() {
        return Arrays.asList(ProjetoRisco.values())
                .stream()
                .collect(Collectors.toMap(status -> status, status -> status.getDescricao()));
    }

    public static List<ProjetoRisco.RiscoRecord> toList() {
        return toMap().entrySet().
                stream().
                map(ProjetoRisco.RiscoRecord::new)
                .toList();
    }

    public record RiscoRecord(String risco, String descricao) {
        public RiscoRecord(Map.Entry<ProjetoRisco, String> entry) {
            this(entry.getKey().toString(), entry.getValue());
        }
    }
}

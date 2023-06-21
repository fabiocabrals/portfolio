package com.spring.portfolio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.portfolio.model.Pessoa;
import com.spring.portfolio.model.Projeto;
import com.spring.portfolio.model.ProjetoRisco;
import com.spring.portfolio.model.ProjetoStatus;
import com.spring.portfolio.repository.PessoaRepository;
import com.spring.portfolio.repository.ProjetoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjetoControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired PessoaRepository pessoaRepository;
    @Autowired ProjetoRepository projetoRepository;

    @BeforeEach
    void up() {
        var gerente = new Pessoa();
        gerente.setNome("Gerente 01");
        gerente.setCpf("98765432100");
        gerente.setFuncionario(true);
        gerente.setDataNascimento(LocalDate.parse("2000-03-06"));
        pessoaRepository.save(gerente);
    }

    @AfterEach
    void down() {
        projetoRepository.deleteAll();
        pessoaRepository.deleteAll();
    }

    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("Listar todos os projetos")
    public void findAll() throws Exception {
        mockMvc.perform(get("/projetos"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Cadastrar um novo projeto")
    public void save() throws Exception {

        String projetoRequest = objectMapper.writeValueAsString(getInstance());

        mockMvc.perform(post("/projetos")
                .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                .content(projetoRequest))
                .andExpect(status().isCreated());
    }

    public Projeto getInstance() {
        var gerente = pessoaRepository.findByCpf("98765432100");
        var projeto = new Projeto();
        projeto.setNome("Projeto Foguete Falcon 9");
        projeto.setDescricao("Lançamento do foguete Falcon 9 para orbita geoestacionaria da Terra.");
        projeto.setDataInicio(LocalDate.parse("2023-08-01"));
        projeto.setDataFim(LocalDate.parse("2023-08-10"));
        projeto.setDataPrevisaoFim(LocalDate.parse("2023-08-10"));
        projeto.setStatus(ProjetoStatus.PLANEJADO);
        projeto.setRisco(ProjetoRisco.MEDIO);
        projeto.setGerente(gerente.get());
        projeto.setOrcamento(BigDecimal.valueOf(8556300.00));
        return projeto;
    }
}

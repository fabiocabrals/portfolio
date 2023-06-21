package com.spring.portfolio.service;

import com.spring.portfolio.model.Projeto;
import com.spring.portfolio.repository.ProjetoRepository;
import com.spring.portfolio.service.impl.ProjetoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class ProjetoServiceTest {

    @Before
    public void setup() {
        long id = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(id);
        Mockito.when(projetoRepository.findById(id)).thenReturn(Optional.of(projeto));
    }

    @TestConfiguration
    static class ProjetoServiceTestConfiguration {
        @Bean
        public ProjetoService projetoService() {
            return new ProjetoServiceImpl();
        }
    }

    @Autowired ProjetoService projetoService;
    @MockBean ProjetoRepository projetoRepository;
    @MockBean PessoaService pessoaService;

    @Test
    public void findById() {
        long id = 1L;
        var projeto = projetoService.findById(id);
        Assertions.assertEquals(id, projeto.get().getId());
    }
}

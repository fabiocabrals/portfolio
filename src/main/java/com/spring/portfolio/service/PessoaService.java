package com.spring.portfolio.service;

import com.spring.portfolio.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PessoaService {
    public Pessoa save(Pessoa pessoa);
    public Optional<Pessoa> findById(Long id);
    public Page<Pessoa> findAll(Pageable pageable);
    public void delete(Pessoa pessoa);
}

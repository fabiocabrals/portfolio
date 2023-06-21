package com.spring.portfolio.service;

import com.spring.portfolio.model.Projeto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProjetoService {
    public Projeto save(Projeto projeto);
    public Optional<Projeto> findById(Long id);
    public Page<Projeto> findAll(Pageable pageable, String status);
    public void delete(Projeto projeto);
}

package com.spring.portfolio.repository;

import com.spring.portfolio.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public Optional<Pessoa> findByCpf(String cpf);
}

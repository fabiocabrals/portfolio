package com.spring.portfolio.repository;

import com.spring.portfolio.model.Projeto;
import com.spring.portfolio.model.ProjetoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query(value = "from Projeto p where p.status =?1")
    public Page<Projeto> findByStatus(Pageable pageable, ProjetoStatus status);
}

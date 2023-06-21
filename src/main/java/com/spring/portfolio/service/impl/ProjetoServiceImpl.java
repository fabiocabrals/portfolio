package com.spring.portfolio.service.impl;

import com.spring.portfolio.exception.BusinessException;
import com.spring.portfolio.model.Projeto;
import com.spring.portfolio.model.ProjetoStatus;
import com.spring.portfolio.repository.ProjetoRepository;
import com.spring.portfolio.service.PessoaService;
import com.spring.portfolio.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    @Autowired private ProjetoRepository projetoRepository;
    @Autowired private PessoaService pessoaService;

    @Override
    public Projeto save(Projeto projeto) {
        if (projeto.getMembros() != null) {
            if (projeto.getMembros().stream().filter(pessoa -> !pessoaService.findById(pessoa.getId()).get().isFuncionario()).count() > 0) {
                throw new BusinessException("O membro deve ser um funcionário");
            }
        }
        return projetoRepository.save(projeto);
    }

    @Override
    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }

    @Override
    public Page<Projeto> findAll(Pageable pageable, String status) {
        if (status != null) {
            return projetoRepository.findByStatus(pageable, ProjetoStatus.valueOf(status));
        }
        return projetoRepository.findAll(pageable);
    }

    @Override
    public void delete(Projeto projeto) {
        if (!podeExcluir(projeto.getStatus())) {
            throw new BusinessException("Projeto não pode ser excluído.");
        }
        projetoRepository.delete(projeto);
    }

    private boolean podeExcluir(ProjetoStatus status) {
        return switch (status) {
            case INICIADO, EM_ANDAMENTO, ENCERRADO -> false;
            default -> true;
        };
    }

}

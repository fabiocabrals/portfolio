package com.spring.portfolio.controller;

import com.spring.portfolio.controller.request.ProjetoMembros;
import com.spring.portfolio.controller.request.ProjetoRecord;
import com.spring.portfolio.model.Projeto;
import com.spring.portfolio.model.ProjetoRisco;
import com.spring.portfolio.model.ProjetoStatus;
import com.spring.portfolio.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired private ProjetoService projetoService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Projeto> save(@RequestBody @Valid ProjetoRecord projetoRecord) {
        var projeto = new Projeto();
        BeanUtils.copyProperties(projetoRecord, projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoService.save(projeto));
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        var projeto = projetoService.findById(id);
        if (projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(projeto.get());
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Page<Projeto>> findAll(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable, @RequestParam(required = false) String status) {
        var projetos = projetoService.findAll(pageable, status);
        return ResponseEntity.status(HttpStatus.OK).body(projetos);
    }

    @CrossOrigin
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ProjetoRecord projetoRecord)  {
        var projeto = projetoService.findById(id);
        if (projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado.");
        }
        var updateProjeto = projeto.get();
        BeanUtils.copyProperties(projetoRecord, updateProjeto);
        return ResponseEntity.status(HttpStatus.OK).body(projetoService.save(updateProjeto));
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var projeto = projetoService.findById(id);
        if (projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado.");
        }

        projetoService.delete(projeto.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin
    @PutMapping(path = "/{id}/membros")
    public ResponseEntity<?> updateMembros(@PathVariable Long id, @RequestBody @Valid ProjetoMembros projetoMembros)  {
        var projeto = projetoService.findById(id);
        if (projeto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado.");
        }
        var updateProjeto = projeto.get();
        BeanUtils.copyProperties(projetoMembros, updateProjeto);
        return ResponseEntity.status(HttpStatus.OK).body(projetoService.save(updateProjeto));
    }

    @CrossOrigin
    @GetMapping(path = "/status")
    public ResponseEntity<?> getListStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(ProjetoStatus.toList());
    }

    @CrossOrigin
    @GetMapping(path = "/riscos")
    public ResponseEntity<?> getListRisco() {
        return ResponseEntity.status(HttpStatus.OK).body(ProjetoRisco.toList());
    }

}

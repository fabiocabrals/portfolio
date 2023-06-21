package com.spring.portfolio.controller;

import com.spring.portfolio.controller.request.PessoaRecord;
import com.spring.portfolio.model.Pessoa;
import com.spring.portfolio.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService personService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody @Valid PessoaRecord pessoaRecord) {
        var pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaRecord, pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(pessoa));
    }

    @CrossOrigin
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        var pessoa = personService.findById(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoa.get());
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Page<Pessoa>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(personService.findAll(pageable));
    }

    @CrossOrigin
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid PessoaRecord pessoaRecord) {
        var pessoa = personService.findById(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }

        var updatePessoa = pessoa.get();
        BeanUtils.copyProperties(pessoaRecord, updatePessoa);
        return ResponseEntity.status(HttpStatus.OK).body(personService.save(updatePessoa));
    }

    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var pessoa = personService.findById(id);
        if (pessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }

        personService.delete(pessoa.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

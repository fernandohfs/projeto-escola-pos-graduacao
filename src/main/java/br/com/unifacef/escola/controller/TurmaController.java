package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.TurmaBusiness;
import br.com.unifacef.escola.contract.response.materia.MateriaResponse;
import br.com.unifacef.escola.contract.response.turma.TurmaResponse;
import br.com.unifacef.escola.contract.response.turma.TurmaResponseList;
import br.com.unifacef.escola.contract.validation.materia.MateriaValidation;
import br.com.unifacef.escola.contract.validation.turma.TurmaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/turmas")
public class TurmaController {

    @Autowired
    TurmaBusiness turmaBusiness;

    @GetMapping
    public ResponseEntity<TurmaResponseList> find(
            @PageableDefault(sort="id", page=0, size=10) Pageable pageable) {
        return ResponseEntity.ok(TurmaResponseList.parse(turmaBusiness.find(pageable)));
    }

    @PostMapping
    public ResponseEntity<TurmaResponse> create(@RequestBody @Valid TurmaValidation turma) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TurmaResponse.parse(turmaBusiness.create(turma.converter())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponse> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok(TurmaResponse.parse(turmaBusiness.findById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponse> update(
            @RequestBody @Valid TurmaValidation turma, @PathVariable Integer id) {
        return ResponseEntity.ok(
                TurmaResponse.parse(turmaBusiness.update(id, turma.converter())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        turmaBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

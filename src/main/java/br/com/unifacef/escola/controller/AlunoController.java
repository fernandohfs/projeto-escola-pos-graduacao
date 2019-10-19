package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.AlunoBusiness;
import br.com.unifacef.escola.contract.response.aluno.AlunoResponse;
import br.com.unifacef.escola.contract.response.aluno.AlunoResponseList;
import br.com.unifacef.escola.contract.validation.aluno.AlunoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoBusiness alunoBusiness;

    @GetMapping
    public ResponseEntity<AlunoResponseList> find(
            @PageableDefault(sort="id", page=0, size=10) Pageable pageable) {
        return ResponseEntity.ok().body(AlunoResponseList.parse(alunoBusiness.find(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok().body(AlunoResponse.parse(alunoBusiness.findBy(id)));
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> create(@RequestBody @Valid AlunoValidation aluno) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AlunoResponse.parse(alunoBusiness.create(aluno.converter())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> update(@PathVariable Integer id, @RequestBody @Valid AlunoValidation aluno) {
        return ResponseEntity.ok().body(AlunoResponse.parse(alunoBusiness.update(id, aluno.converter())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        alunoBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.TurmaBusiness;
import br.com.unifacef.escola.contract.response.aluno.AlunoResponse;
import br.com.unifacef.escola.contract.response.curso.CursoResponse;
import br.com.unifacef.escola.contract.response.materia.MateriaResponse;
import br.com.unifacef.escola.contract.response.turma.TurmaResponse;
import br.com.unifacef.escola.contract.response.turma.TurmaResponseList;
import br.com.unifacef.escola.contract.validation.aluno.AlunoFlexibleValidation;
import br.com.unifacef.escola.contract.validation.materia.MateriaValidation;
import br.com.unifacef.escola.contract.validation.turma.TurmaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<TurmaResponse> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok(TurmaResponse.parse(turmaBusiness.findById(id)));
    }

    @GetMapping("/{id}/alunos")
    public ResponseEntity<List<AlunoResponse>> findByIdWithAlunos(@PathVariable Integer id) {
        return ResponseEntity.ok(AlunoResponse.parse(turmaBusiness.findById(id).getAlunos()));
    }

    @PostMapping
    public ResponseEntity<TurmaResponse> create(@RequestBody @Valid TurmaValidation turma) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TurmaResponse.parse(turmaBusiness.create(turma.converter())));
    }

    @PostMapping("/{id}/alunos")
    public ResponseEntity<?> createAttachAlunos(@PathVariable Integer id, @RequestBody List<@Valid AlunoFlexibleValidation> alunos) {
        return ResponseEntity
                .ok(AlunoResponse.parse(turmaBusiness.attachAluno(id, alunos)));
    }

    @PostMapping("/{idTurma}/alunos/{idAluno}")
    public ResponseEntity<?> createAttachAluno(@PathVariable Integer idTurma, @PathVariable Integer idAluno) {
        return ResponseEntity
                .ok(AlunoResponse.parse(turmaBusiness.attachAluno(idTurma, idAluno)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaResponse> update(
            @RequestBody @Valid TurmaValidation turma, @PathVariable Integer id) {
        return ResponseEntity.ok(
                TurmaResponse.parse(turmaBusiness.update(id, turma.converter())));
    }

    @PutMapping("/{id}/alunos")
    public ResponseEntity<List<AlunoResponse>> updateSyncAlunos(@PathVariable Integer id, @RequestBody @Valid List<AlunoFlexibleValidation> alunos) {
        return ResponseEntity
                .ok(AlunoResponse.parse(turmaBusiness.syncAlunos(id, alunos)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        turmaBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idTurma}/alunos/{idAluno}")
    public ResponseEntity<?> detachAluno(@PathVariable Integer idTurma, @PathVariable Integer idAluno) {
        turmaBusiness.detachAluno(idTurma, idAluno);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/alunos")
    public ResponseEntity<?> detachAllAlunos(@PathVariable Integer id, @RequestBody @Valid List<AlunoFlexibleValidation> alunos) {
        turmaBusiness.detachAluno(id, alunos);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

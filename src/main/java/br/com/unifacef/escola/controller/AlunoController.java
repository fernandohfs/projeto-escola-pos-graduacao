package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.AlunoBusiness;
import br.com.unifacef.escola.contract.response.aluno.AlunoResponse;
import br.com.unifacef.escola.contract.response.aluno.AlunoResponseList;
import br.com.unifacef.escola.contract.response.aluno.AlunoTurmaResponse;
import br.com.unifacef.escola.contract.response.turma.SimpleTurmaResponse;
import br.com.unifacef.escola.contract.validation.aluno.AlunoValidation;
import br.com.unifacef.escola.contract.validation.turma.TurmaFlexibleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoBusiness alunoBusiness;

    @GetMapping
    public ResponseEntity<AlunoResponseList> find(@RequestParam(required = false) String nome,
            @PageableDefault(sort="id", page=0, size=10) Pageable pageable) {
        if (nome != null) {
            return ResponseEntity.ok().body(AlunoResponseList.parse(alunoBusiness.findByNome(nome, pageable)));
        }

        return ResponseEntity.ok().body(AlunoResponseList.parse(alunoBusiness.find(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok().body(AlunoResponse.parse(alunoBusiness.findBy(id)));
    }

    @GetMapping("/{id}/turmas")
    public ResponseEntity<List<AlunoTurmaResponse>> findByIdWithTurmas(@PathVariable Integer id) {
        return ResponseEntity.ok(AlunoTurmaResponse.parse(alunoBusiness.findBy(id).getTurmas()));
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> create(@RequestBody @Valid AlunoValidation aluno) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AlunoResponse.parse(alunoBusiness.create(aluno.converter())));
    }

    @PostMapping("/{id}/turmas")
    public ResponseEntity<?> createAttachTurmas(@PathVariable Integer id, @RequestBody List<@Valid TurmaFlexibleValidation> turmas) {
        return ResponseEntity
                .ok(SimpleTurmaResponse.parse(alunoBusiness.attachTurma(id, turmas)));
    }

    @PostMapping("/{idAluno}/turmas/{idTurma}")
    public ResponseEntity<?> createAttachTurma(@PathVariable Integer idAluno, @PathVariable Integer idTurma) {
        return ResponseEntity
                .ok(SimpleTurmaResponse.parse(alunoBusiness.attachTurma(idAluno, idTurma)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> update(@PathVariable Integer id, @RequestBody @Valid AlunoValidation aluno) {
        return ResponseEntity.ok().body(AlunoResponse.parse(alunoBusiness.update(id, aluno.converter())));
    }

    @PutMapping("/{id}/turmas")
    public ResponseEntity<List<SimpleTurmaResponse>> updateSyncTurmas(@PathVariable Integer id, @RequestBody @Valid List<TurmaFlexibleValidation> turmas) {
        return ResponseEntity
                .ok(SimpleTurmaResponse.parse(alunoBusiness.syncTurmas(id, turmas)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        alunoBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idAluno}/turmas/{idTurma}")
    public ResponseEntity<?> detachTurma(@PathVariable Integer idAluno, @PathVariable Integer idTurma) {
        alunoBusiness.detach(idAluno, idTurma);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/turmas")
    public ResponseEntity<?> detachAllTurma(@PathVariable Integer id, @RequestBody @Valid List<TurmaFlexibleValidation> turmas) {
        alunoBusiness.detach(id, turmas);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

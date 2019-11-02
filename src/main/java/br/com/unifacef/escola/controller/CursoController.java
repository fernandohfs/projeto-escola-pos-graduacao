package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.contract.response.curso.CursoMateriaResponse;
import br.com.unifacef.escola.contract.response.curso.CursoResponse;
import br.com.unifacef.escola.contract.response.curso.CursoResponseList;
import br.com.unifacef.escola.contract.response.materia.SimpleMateriaResponse;
import br.com.unifacef.escola.contract.response.turma.SimpleTurmaResponse;
import br.com.unifacef.escola.contract.validation.curso.CursoValidation;
import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.contract.validation.turma.TurmaFlexibleValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/cursos")
public class CursoController {

    @Autowired
    private CursoBusiness cursoBusiness;

    @GetMapping
    public ResponseEntity<CursoResponseList> find(
            @PageableDefault(sort="id", page=0, size=10) Pageable pageable) {
        return ResponseEntity.ok().body(CursoResponseList.parse(cursoBusiness.find(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok().body(CursoResponse.parse(cursoBusiness.findBy(id)));
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<List<CursoMateriaResponse>> findByIdWithMaterias(@PathVariable Integer id) {
        return ResponseEntity.ok(CursoMateriaResponse.parse(cursoBusiness.findBy(id).getMaterias()));
    }

    @GetMapping("/{id}/turmas")
    public ResponseEntity<List<SimpleTurmaResponse>> findByIdWithTurmas(@PathVariable Integer id) {
        return ResponseEntity.ok(SimpleTurmaResponse.parse(cursoBusiness.findBy(id).getTurmas()));
    }

    @PostMapping
    public ResponseEntity<CursoResponse> create(@RequestBody @Valid CursoValidation curso) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CursoResponse.parse(cursoBusiness.create(curso.converter())));
    }

    @PostMapping("/{id}/materias")
    public ResponseEntity<?> createAttachMaterias(@PathVariable Integer id, @RequestBody List<@Valid MateriaFlexibleValidation> materias) {
        return ResponseEntity
                .ok(SimpleMateriaResponse.parse(cursoBusiness.attachMateria(id, materias)));
    }

    @PostMapping("/{id}/turmas")
    public ResponseEntity<?> createAttachTurmas(@PathVariable Integer id, @RequestBody List<@Valid TurmaFlexibleValidation> turmas) {
        return ResponseEntity
                .ok(SimpleTurmaResponse.parse(cursoBusiness.attachTurma(id, turmas)));
    }

    @PostMapping("/{idCurso}/materias/{idMateria}")
    public ResponseEntity<?> createAttachMateria(@PathVariable Integer idCurso, @PathVariable Integer idMateria) {
        return ResponseEntity
                .ok(SimpleMateriaResponse.parse(cursoBusiness.attachMateria(idCurso, idMateria)));
    }

    @PostMapping("/{idCurso}/turmas/{idTurma}")
    public ResponseEntity<SimpleTurmaResponse> createAttachTurmas(@PathVariable Integer idCurso, @PathVariable Integer idTurma) {
        return ResponseEntity
                .ok(SimpleTurmaResponse.parse(cursoBusiness.attachTurma(idCurso, idTurma)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> update(@PathVariable Integer id, @RequestBody @Valid CursoValidation curso) {
        return ResponseEntity.ok().body(CursoResponse.parse(cursoBusiness.update(id, curso.converter())));
    }

    @PutMapping("/{id}/materias")
    public ResponseEntity<List<SimpleMateriaResponse>> updateSyncMaterias(@PathVariable Integer id, @RequestBody @Valid List<MateriaFlexibleValidation> materias) {
        return ResponseEntity
                .ok(SimpleMateriaResponse.parse(cursoBusiness.syncMaterias(id, materias)));
    }

    @PutMapping("/{id}/turmas")
    public ResponseEntity<List<SimpleTurmaResponse>> updateSyncTurmas(@PathVariable Integer id, @RequestBody @Valid List<TurmaFlexibleValidation> turmas) {
        return ResponseEntity
                .ok(SimpleTurmaResponse.parse(cursoBusiness.syncTurmas(id, turmas)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cursoBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idCurso}/materias/{idMateria}")
    public ResponseEntity<?> detachMateria(@PathVariable Integer idCurso, @PathVariable Integer idMateria) {
        cursoBusiness.detach(idCurso, idMateria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/materias")
    public ResponseEntity<?> detachAllMateria(@PathVariable Integer id, @RequestBody @Valid List<MateriaFlexibleValidation> materias) {
        cursoBusiness.detach(id, materias);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idCurso}/turmas/{idTurma}")
    public ResponseEntity<?> detachTurma(@PathVariable Integer idTurma) {
        cursoBusiness.detachTurma(idTurma);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/turmas")
    public ResponseEntity<?> detachAllTurma(@PathVariable Integer id, @RequestBody @Valid List<TurmaFlexibleValidation> turmas) {
        cursoBusiness.detachTurma(turmas);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
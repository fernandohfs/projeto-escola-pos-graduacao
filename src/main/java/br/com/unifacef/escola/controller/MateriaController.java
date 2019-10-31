package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.contract.response.curso.CursoResponse;
import br.com.unifacef.escola.contract.response.materia.MateriaResponse;
import br.com.unifacef.escola.contract.response.materia.MateriaResponseList;
import br.com.unifacef.escola.contract.validation.curso.CursoFlexibleValidation;
import br.com.unifacef.escola.contract.validation.materia.MateriaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/materias")
public class MateriaController {

    @Autowired
    private MateriaBusiness materiaBusiness;

    @GetMapping
    public ResponseEntity<MateriaResponseList> find(
            @PageableDefault(sort="id", page=0, size=10) Pageable pageable) {
        return ResponseEntity.ok(MateriaResponseList.parse(materiaBusiness.find(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaResponse> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok(MateriaResponse.parse(materiaBusiness.findById(id)));
    }

    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<CursoResponse>> findByIdWithCursos(@PathVariable Integer id) {
        return ResponseEntity.ok(CursoResponse.parse(materiaBusiness.findById(id).getCursos()));
    }

    @PostMapping
    public ResponseEntity<MateriaResponse> create(@RequestBody @Valid MateriaValidation materia) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(MateriaResponse.parse(materiaBusiness.create(materia.converter())));
    }

    @PostMapping("/{id}/cursos")
    public ResponseEntity<?> createAttachCursos(@PathVariable Integer id, @RequestBody List<@Valid CursoFlexibleValidation> cursos) {
        return ResponseEntity
                .ok(CursoResponse.parse(materiaBusiness.attachCurso(id, cursos)));
    }

    @PostMapping("/{idMateria}/cursos/{idCurso}")
    public ResponseEntity<?> createAttachCurso(@PathVariable Integer idMateria, @PathVariable Integer idCurso) {
        return ResponseEntity
                .ok(CursoResponse.parse(materiaBusiness.attachCurso(idMateria, idCurso)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaResponse> update(
            @RequestBody @Valid MateriaValidation materia, @PathVariable Integer id) {
        return ResponseEntity.ok(
                MateriaResponse.parse(materiaBusiness.update(id, materia.converter())));
    }

    @PutMapping("/{id}/cursos")
    public ResponseEntity<List<CursoResponse>> updateSyncCursos(@PathVariable Integer id, @RequestBody @Valid List<CursoFlexibleValidation> cursos) {
        return ResponseEntity
                .ok(CursoResponse.parse(materiaBusiness.syncCursos(id, cursos)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        materiaBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idMateria}/cursos/{idCurso}")
    public ResponseEntity<?> detachCurso(@PathVariable Integer idMateria, @PathVariable Integer idCurso) {
        materiaBusiness.detachCurso(idMateria, idCurso);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/cursos")
    public ResponseEntity<?> detachAllCursos(@PathVariable Integer id, @RequestBody @Valid List<CursoFlexibleValidation> cursos) {
        materiaBusiness.detachCurso(id, cursos);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

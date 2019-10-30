package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.contract.response.curso.CursoMateriaResponse;
import br.com.unifacef.escola.contract.response.curso.CursoResponse;
import br.com.unifacef.escola.contract.response.materia.SimpleMateriaResponse;
import br.com.unifacef.escola.contract.validation.curso.CursoValidation;
import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    /*@GetMapping
    public ResponseEntity<CursoResponseList> find(
            @PageableDefault(sort="id", page=0, size=10) Pageable pageable) {
        return ResponseEntity.ok().body(CursoResponseList.parse(cursoBusiness.find(pageable)));
    }*/

    @GetMapping
    public ResponseEntity<Page<Curso>> find(
            @PageableDefault(sort="id", page=0, size=10) Pageable pageable) {
        return ResponseEntity.ok().body(cursoBusiness.find(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok().body(CursoResponse.parse(cursoBusiness.findBy(id)));
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<List<CursoMateriaResponse>> findByIdWithMaterias(@PathVariable Integer id) {
        return ResponseEntity.ok(CursoMateriaResponse.parse(cursoBusiness.findBy(id).getMaterias()));
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

    @PostMapping("/{idProfessor}/materias/{idMateria}")
    public ResponseEntity<?> createAttachMaterias(@PathVariable Integer idProfessor, @PathVariable Integer idMateria) {
        return ResponseEntity
                .ok(SimpleMateriaResponse.parse(cursoBusiness.attachMateria(idProfessor, idMateria)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> update(@PathVariable Integer id, @RequestBody @Valid CursoValidation curso) {
        return ResponseEntity.ok().body(CursoResponse.parse(cursoBusiness.update(id, curso.converter())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        cursoBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
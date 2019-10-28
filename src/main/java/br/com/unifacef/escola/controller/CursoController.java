package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.contract.response.curso.CursoResponse;
import br.com.unifacef.escola.contract.response.curso.CursoResponseList;
import br.com.unifacef.escola.contract.validation.curso.CursoValidation;
import br.com.unifacef.escola.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping
    public ResponseEntity<CursoResponse> create(@RequestBody @Valid CursoValidation curso) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CursoResponse.parse(cursoBusiness.create(curso.converter())));
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
package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/cursos")
public class CursoController {

  @Autowired
  private CursoBusiness cursoBusiness;

  @GetMapping
  public ResponseEntity<List<Curso>> find() {
    return ResponseEntity.ok().body(cursoBusiness.find());
  }

  @GetMapping("/{id}")
  public Optional<Curso> findBy(@PathVariable Integer id) {
    return cursoBusiness.findBy(id);
  }

  @PostMapping
  public ResponseEntity<Curso> create(@RequestBody Curso curso) {
    Curso createdCurso = cursoBusiness.create(curso);

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdCurso);
  }

  @PutMapping("/{id}")
  public Curso update(@PathVariable Integer id, @RequestBody Curso curso) {
      return cursoBusiness.update(id, curso);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Integer id) {
      cursoBusiness.delete(id);

      return ResponseEntity.noContent().build();
  }
}
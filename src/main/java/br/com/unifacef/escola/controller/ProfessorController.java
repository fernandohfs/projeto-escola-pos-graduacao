package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.Impl.ProfessorBusinessImpl;
import br.com.unifacef.escola.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {

    @Autowired
    private ProfessorBusinessImpl professorBusiness;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
        return ResponseEntity.ok(professorBusiness.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id) {
        Professor professor = professorBusiness.findBy(id);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        return ResponseEntity.ok(professorBusiness.create(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> update(
            @RequestBody Professor professor, @PathVariable Integer id) {
        return ResponseEntity.ok(professorBusiness.update(id, professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        professorBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

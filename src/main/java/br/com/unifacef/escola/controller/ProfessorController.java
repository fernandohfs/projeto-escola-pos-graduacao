package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.Impl.ProfessorBusinessImpl;
import br.com.unifacef.escola.contract.returnJson.professor.ProfessorReturn;
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
    public ResponseEntity<List<ProfessorReturn>> findAll() {
        return ResponseEntity.ok(professorBusiness.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorReturn> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(professorBusiness.findBy(id));
    }

    @PostMapping
    public ResponseEntity<ProfessorReturn> create(@RequestBody Professor professor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(professorBusiness.create(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorReturn> update(
            @RequestBody Professor professor, @PathVariable Integer id) {
        return ResponseEntity.ok(professorBusiness.update(id, professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        professorBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

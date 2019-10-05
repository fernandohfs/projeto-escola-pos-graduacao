package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.Impl.ProfessorBusinessImpl;
import br.com.unifacef.escola.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {

    private ProfessorBusinessImpl professorBusiness;

    @Autowired
    public ProfessorController(ProfessorBusinessImpl professorBusiness) {
        this.professorBusiness = professorBusiness;
    }

    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
        List<Professor> professores = professorBusiness.findAll();
        return ResponseEntity.ok().body(professores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Professor>> findBy(@PathVariable Integer id) {
        List<Professor> professores = professorBusiness.findBy(id);
        return ResponseEntity.ok().body(professores);
    }

    @PostMapping
    public ResponseEntity<Professor> create(@RequestBody Professor professor) {
        return ResponseEntity.ok().body(professorBusiness.create(professor));
    }

}

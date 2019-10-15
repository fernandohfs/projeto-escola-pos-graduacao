package br.com.unifacef.escola.controllers;

import br.com.unifacef.escola.business.AlunoBusiness;
import br.com.unifacef.escola.model.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/alunos")
public class AlunoController {

    @Autowired
    private AlunoBusiness alunoBusiness;

    @GetMapping
    public ResponseEntity<List<Aluno>> find() {
        return ResponseEntity.ok().body(alunoBusiness.find());
    }

    @GetMapping("/{id}")
    public Optional<Aluno> findBy(@PathVariable Integer id) {
        return alunoBusiness.findBy(id);
    }

    @PostMapping
    public ResponseEntity<Aluno> create(@RequestBody Aluno aluno) {
        Aluno createdAluno = alunoBusiness.create(aluno);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id, @RequestBody Aluno aluno) {
        return alunoBusiness.update(id, aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        alunoBusiness.delete(id);

        return ResponseEntity.noContent().build();
    }
}

package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.contract.response.materia.MateriaResponse;
import br.com.unifacef.escola.contract.validation.materia.MateriaValidation;
import br.com.unifacef.escola.model.Materia;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<MateriaResponse>> find() {
        return ResponseEntity.ok(materiaBusiness.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaResponse> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok(materiaBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<MateriaResponse> create(@RequestBody @Valid MateriaValidation materia) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(materiaBusiness.create(materia.converter()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaResponse> update(
            @RequestBody @Valid MateriaValidation materia, @PathVariable Integer id) {
        return ResponseEntity.ok(materiaBusiness.update(id, materia.converter()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        materiaBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

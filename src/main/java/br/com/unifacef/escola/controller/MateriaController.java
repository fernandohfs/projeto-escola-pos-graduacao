package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.contract.returnJson.MateriaReturn;
import br.com.unifacef.escola.model.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/materias")
public class MateriaController {

    @Autowired
    private MateriaBusiness materiaBusiness;

    @GetMapping
    public ResponseEntity<List<MateriaReturn>> find() {
        return ResponseEntity.ok(materiaBusiness.find());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaReturn> findBy(@PathVariable Integer id) {
        return ResponseEntity.ok(materiaBusiness.findById(id));
    }

    @PostMapping
    public ResponseEntity<MateriaReturn> create(@RequestBody Materia materia) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(materiaBusiness.create(materia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaReturn> update(
            @RequestBody Materia materia, @PathVariable Integer id) {
        return ResponseEntity.ok(materiaBusiness.update(id, materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        materiaBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

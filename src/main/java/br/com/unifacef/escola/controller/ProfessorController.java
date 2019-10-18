package br.com.unifacef.escola.controller;

import br.com.unifacef.escola.business.ProfessorBusiness;
import br.com.unifacef.escola.contract.response.professor.ProfessorMateriaResponse;
import br.com.unifacef.escola.contract.response.professor.ProfessorResponse;
import br.com.unifacef.escola.contract.validation.ProfessorMateriaValidation;
import br.com.unifacef.escola.contract.validation.ProfessorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/professores")
public class ProfessorController {

    @Autowired
    private ProfessorBusiness professorBusiness;

    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> findAll() {
        return ResponseEntity.ok(ProfessorResponse.parse(professorBusiness.find()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(ProfessorResponse.parse(professorBusiness.findBy(id)));
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<List<ProfessorMateriaResponse>> findByIdWithMaterias(@PathVariable Integer id) {
        return ResponseEntity.ok(ProfessorMateriaResponse.parse(professorBusiness.findBy(id).getMaterias()));
    }

    @PostMapping
    public ResponseEntity<ProfessorResponse> create(@RequestBody ProfessorValidation professor) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProfessorResponse.parse(professorBusiness.create(professor.converter())));
    }

    @PostMapping("/{id}/materias")
    public ResponseEntity<List<ProfessorMateriaResponse>> createAttachMaterias(@PathVariable Integer id, @RequestBody List<ProfessorMateriaValidation> materias) {
        return ResponseEntity
                .ok(ProfessorMateriaResponse.parse(professorBusiness.attachMateria(id, materias)));
    }

    @PostMapping("/{idProfessor}/materias/{idMateria}")
    public ResponseEntity<ProfessorMateriaResponse> createAttachMaterias(@PathVariable Integer idProfessor, @PathVariable Integer idMateria) {
        return ResponseEntity
                .ok(ProfessorMateriaResponse.parse(professorBusiness.attachMateria(idProfessor, idMateria)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponse> update(
            @RequestBody ProfessorValidation professor, @PathVariable Integer id) {
        return ResponseEntity.ok(ProfessorResponse.parse(professorBusiness.update(id, professor.converter())));
    }

    @PutMapping("/{id}/materias")
    public ResponseEntity<List<ProfessorMateriaResponse>> updateSyncMaterias(@PathVariable Integer id, @RequestBody List<ProfessorMateriaValidation> materias) {
        return ResponseEntity
                .ok(ProfessorMateriaResponse.parse(professorBusiness.syncMaterias(id, materias)));
    }

    @DeleteMapping("/{idProfessor}/materias/{idMateria}")
    public ResponseEntity<?> detachMateria(@PathVariable Integer idMateria) {
        professorBusiness.detach(idMateria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}/materias")
    public ResponseEntity<?> detachAllMateria(@PathVariable Integer id, @RequestBody List<ProfessorMateriaValidation> materias) {
        professorBusiness.detach(materias);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        professorBusiness.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.business.ProfessorBusiness;
import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import br.com.unifacef.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorBusinessImpl implements ProfessorBusiness {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private MateriaBusiness materiaBusiness;

    @Override
    public Page<Professor> find(Pageable pageable) {
        return professorRepository.findAll(pageable);
    }

    @Override
    public Professor findBy(Integer id) {
        return professorRepository.getOne(id);
    }

    @Override
    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor update(Integer id, Professor professorUpdate) {
        Professor professor = professorRepository.getOne(id);
        professor.update(professorUpdate);
        return professorRepository.save(professor);
    }

    @Override
    public void delete(Integer id) {
        professorRepository.deleteById(id);
    }

    @Override
    public List<Materia> attachMateria(Integer id, List<MateriaFlexibleValidation> professorMaterias) {
        List<Materia> materias =  new ArrayList<>();
        Professor professor = findBy(id);

        professorMaterias.forEach(materia -> {
            materias.add(materiaBusiness.attach(materia.getId(), professor));
        });

        return materias;
    }

    @Override
    public Materia attachMateria(Integer idProfessor, Integer idMateria) {
        Professor professor = findBy(idProfessor);
        return materiaBusiness.attach(idMateria, professor);
    }

    @Override
    public List<Materia> syncMaterias(Integer id, List<MateriaFlexibleValidation> professorMaterias) {
        List<Materia> materias = MateriaFlexibleValidation.converterList(professorMaterias);
        Professor professor = findBy(id);
        return materiaBusiness.sync(materias, professor);
    }

    @Override
    public void detach(Integer idMateria) {
        materiaBusiness.detach(idMateria);
    }

    @Override
    public void detach(List<MateriaFlexibleValidation> materias) {
        materias.forEach(materia -> {
            detach(materia.getId());
        });
    }

    @Override
    public void detachAll(Integer idProfessor) {
        materiaBusiness.detachAll(idProfessor);
    }

}

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

import java.util.List;
import java.util.stream.Collectors;

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
        return professorRepository.findById(id).get();
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
    public List<Materia> attachMaterias(Integer idProfessor, List<MateriaFlexibleValidation> professorMaterias) {
        Professor professor = findBy(idProfessor);
        return materiaBusiness.attachMaterias(MateriaFlexibleValidation.converterList(professorMaterias), professor);
    }

    @Override
    public List<Materia> attachMateria(Integer idProfessor, Integer idMateria) {
        Professor professor = findBy(idProfessor);
        Materia materia = materiaBusiness.attachProfessor(idMateria, professor);
        return professor.getMaterias().stream().filter(m -> m.getId() != materia.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Materia> syncMaterias(Integer idProfessor, List<MateriaFlexibleValidation> professorMaterias) {
        Professor professor = findBy(idProfessor);
        return materiaBusiness.syncProfessor(MateriaFlexibleValidation.converterList(professorMaterias), professor);
    }

    @Override
    public void detachMateria(Integer idProfessor, Integer idMateria) {
        Professor professor = findBy(idProfessor);
        materiaBusiness.detachProfessor(idMateria);
    }

    @Override
    public void detachMaterias(Integer idProfessor, List<MateriaFlexibleValidation> professoMaterias) {
        Professor professor = findBy(idProfessor);

        professoMaterias.forEach(materia -> {
            materiaBusiness.detachProfessor(materia.getId());
        });

        professorRepository.save(professor);
    }

    @Override
    public void detachAllMaterias(Integer idProfessor) {
        Professor professor = findBy(idProfessor);
        materiaBusiness.detachProfessor(professor.getId());
    }

}

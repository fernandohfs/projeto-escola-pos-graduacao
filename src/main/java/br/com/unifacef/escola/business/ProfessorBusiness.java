package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProfessorBusiness {

    public Page<Professor> find(Pageable pageable);
    public Professor findBy(Integer id);
    public Professor create(Professor professor);
    public Professor update(Integer id, Professor professor);
    public void delete(Integer id);
    public List<Materia> attachMaterias(Integer id, List<MateriaFlexibleValidation> materias);
    public List<Materia> attachMateria(Integer idProfessor, Integer idMateria);
    public List<Materia> syncMaterias(Integer id, List<MateriaFlexibleValidation> materias);
    public void detachMateria(Integer idProfessor, Integer idMateria);
    public void detachMaterias(Integer idProfessor, List<MateriaFlexibleValidation> materias);
    public void detachAllMaterias(Integer idProfessor);
}

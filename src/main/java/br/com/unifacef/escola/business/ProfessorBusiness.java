package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.validation.professor.ProfessorMateriaValidation;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;

import java.util.List;

public interface ProfessorBusiness {

    public List<Professor> find();
    public Professor findBy(Integer id);
    public Professor create(Professor professor);
    public Professor update(Integer id, Professor professor);
    public void delete(Integer id);
    public List<Materia> attachMateria(Integer id, List<ProfessorMateriaValidation> materias);
    public Materia attachMateria(Integer idProfessor, Integer idMateria);
    public List<Materia> syncMaterias(Integer id, List<ProfessorMateriaValidation> materias);
    public void detach(Integer idMateria);
    public void detach(List<ProfessorMateriaValidation> materias);
    public void detachAll(Integer idProfessor);
}

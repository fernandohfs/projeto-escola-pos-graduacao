package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.validation.curso.CursoFlexibleValidation;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MateriaBusiness {

    public Page<Materia> find(Pageable pageable);
    public Materia findById(Integer id);
    public Materia create(Materia materia);
    public Materia update(Integer id, Materia materiaUpdate);
    public void delete(Integer id);
    //Professor
    public Materia attachProfessor(Integer idMateria, Professor professor);
    public List<Materia> attachMaterias(List<Materia> materias, Professor professor);
    public List<Materia> syncProfessor(List<Materia> materias, Professor professor);
    public void detachProfessor(Integer idMateria);
    public void detachAllProfessor(Integer idProfessor);
    //Curso
    public List<Curso> attachCurso(Integer idMateria, List<CursoFlexibleValidation> materiaCursos);
    public List<Curso> attachCurso(Integer idMateria, Integer idCurso);
    public List<Curso> syncCursos(Integer idMateria, List<CursoFlexibleValidation> materiaCursos);
    public void detachCurso(Integer idMateria, Integer idCurso);
    public void detachCurso(Integer idMateria, List<CursoFlexibleValidation> materiaCursos);
    public void detachAllCurso(Integer idMateria);

}

package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.contract.validation.turma.TurmaFlexibleValidation;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CursoBusiness {
  
  Page<Curso> find(Pageable pageable);
  Curso findBy(Integer id);
  Curso create(Curso curso);
  Curso update(Integer id, Curso curso);
  void delete(Integer id);
  public List<Materia> attachMateria(Integer idCurso, List<MateriaFlexibleValidation> cursoMaterias);
  public List<Materia> attachMateria(Integer idCurso, Integer idMateria);
  public List<Materia> syncMaterias(Integer idCurso, List<MateriaFlexibleValidation> cursoMaterias);
  public List<Turma> attachTurma(Integer idCurso, List<TurmaFlexibleValidation> cursoTurmas);
  public Turma attachTurma(Integer idCurso, Integer idTurma);
  public List<Turma> syncTurmas(Integer idCurso, List<TurmaFlexibleValidation> cursoTurmas);
  public void detach(Integer idCurso, Integer idMateria);
  public void detach(Integer idCurso, List<MateriaFlexibleValidation> cursoMaterias);
  public void detachAll(Integer idCurso);
  public void detachTurma(Integer idTurma);
  public void detachTurma(List<TurmaFlexibleValidation> turmas);
  
}
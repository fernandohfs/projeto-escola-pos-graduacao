package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Materia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CursoBusiness {
  
  Page<Curso> find(Pageable pageable);
  Curso findBy(Integer id);
  Curso create(Curso curso);
  Curso update(Integer id, Curso curso);
  void delete(Integer id);
  public List<Materia> attachMateria(Integer id, List<MateriaFlexibleValidation> cursoMaterias);
  public List<Materia> attachMateria(Integer idCurso, Integer idMateria);
  public List<Materia> syncMaterias(Integer id, List<MateriaFlexibleValidation> cursoMaterias);
  public void detach(Integer idMateria);
  public void detach(List<MateriaFlexibleValidation> cursoMaterias);
  public void detachAll(Integer idCurso);
  
}
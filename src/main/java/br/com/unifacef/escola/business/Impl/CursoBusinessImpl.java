package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CursoBusinessImpl implements CursoBusiness {

  @Autowired
  private CursoRepository cursoRepository;

  @Autowired
  private MateriaBusiness materiaBusiness;

  @Override
  public Page<Curso> find(Pageable pageable) {
    return cursoRepository.findAll(pageable);
  }

  @Override
  public Curso findBy(Integer id) {
    return cursoRepository.getOne(id);
  }

  @Override
  public Curso create(Curso curso) {
    return cursoRepository.save(curso);
  }

  @Override
  public Curso update(Integer id, Curso cursoUpdate) {
    Curso curso = cursoRepository.getOne(id);
    curso.update(cursoUpdate);
    return cursoRepository.save(curso);
  }

  @Override
  public void delete(Integer id) {
    cursoRepository.deleteById(id);
  }

  @Override
  public List<Materia> attachMateria(Integer id, List<MateriaFlexibleValidation> cursoMaterias) {
    Curso curso = findBy(id);
    saveAttachMateria(curso, cursoMaterias);
    return curso.getMaterias();
  }

  @Override
  public List<Materia> attachMateria(Integer idCurso, Integer idMateria) {
    Curso curso = findBy(idCurso);
    Materia materia = materiaBusiness.findById(idMateria);
    saveAttachMateria(curso, materia);
    return curso.getMaterias();
  }

  private void saveAttachMateria(Curso curso, List<MateriaFlexibleValidation> cursoMaterias) {
    List<Materia> materias = curso.getMaterias();
    cursoMaterias.forEach(materia -> {
      materias.add(materia.converter());
    });

    curso.setMaterias(materias);
    cursoRepository.save(curso);
  }

  private void saveAttachMateria(Curso curso,  Materia materia) {
    List<Materia> materias = curso.getMaterias();
    materias.add(materia);

    curso.setMaterias(materias);
    cursoRepository.save(curso);
  }

  @Override
  public List<Materia> syncMaterias(Integer id, List<MateriaFlexibleValidation> materias) {
    return null;
  }

  @Override
  public void detach(Integer idMateria) {

  }

  @Override
  public void detach(List<MateriaFlexibleValidation> materias) {

  }

  @Override
  public void detachAll(Integer idCurso) {

  }

}
package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.business.TurmaBusiness;
import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.contract.validation.turma.TurmaFlexibleValidation;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Turma;
import br.com.unifacef.escola.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoBusinessImpl implements CursoBusiness {

  @Autowired
  private CursoRepository cursoRepository;

  @Autowired
  private MateriaBusiness materiaBusiness;

  @Autowired
  private TurmaBusiness turmaBusiness;

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

  private void saveAttachTurma(Curso curso, List<TurmaFlexibleValidation> cursoTurmas) {
    List<Turma> turmas = curso.getTurmas();
    cursoTurmas.forEach(turma -> {
      turmas.add(turma.converter());
    });

    curso.setTurmas(turmas);
    cursoRepository.save(curso);
  }

  private void saveAttachTurma(Curso curso,  Turma turma) {
    List<Turma> turmas = curso.getTurmas();
    turmas.add(turma);

    curso.setTurmas(turmas);
    cursoRepository.save(curso);
  }

  @Override
  public List<Materia> syncMaterias(Integer idCurso, List<MateriaFlexibleValidation> materias) {
    detachAll(idCurso);
    return attachMateria(idCurso, materias);
  }

  @Override
  public List<Turma> attachTurma(Integer idCurso, List<TurmaFlexibleValidation> cursoTurmas) {
    Curso curso = findBy(idCurso);
    saveAttachTurma(curso, cursoTurmas);
    return curso.getTurmas();
  }

  @Override
  public Turma attachTurma(Integer idCurso, Integer idTurma) {
    Curso curso = findBy(idCurso);
    return turmaBusiness.attach(idTurma, curso);
  }

  @Override
  public List<Turma> syncTurmas(Integer idCurso, List<TurmaFlexibleValidation> cursoTurmas) {
    detachAll(idCurso);
    return attachTurma(idCurso, cursoTurmas);
  }

  @Override
  public void detach(Integer idCurso, Integer idMateria) {
    Curso curso = findBy(idCurso);
    List<Materia> materias = curso.getMaterias().stream().filter(materia -> materia.getId() != idMateria).collect(Collectors.toList());
    curso.setMaterias(materias);
    cursoRepository.save(curso);
  }

  @Override
  public void detach(Integer idCurso, List<MateriaFlexibleValidation> cursoMaterias) {
    Curso curso = findBy(idCurso);

    cursoMaterias.forEach(materia -> {
      curso.setMaterias(
              curso.getMaterias().stream().filter(m -> m.getId() != materia.getId()).collect(Collectors.toList())
      );
    });

    cursoRepository.save(curso);
  }

  @Override
  public void detachAll(Integer idCurso) {
    Curso curso = findBy(idCurso);
    curso.setMaterias(new ArrayList<Materia>());
    cursoRepository.save(curso);
  }

  @Override
  public void detachTurma(Integer idTurma) {
    turmaBusiness.detach(idTurma);
  }

  @Override
  public void detachTurma(List<TurmaFlexibleValidation> turmas) {
    turmas.forEach(turma -> {
      detachTurma(turma.getId());
    });
  }

}
package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoBusinessImpl implements CursoBusiness {

  @Autowired
  private CursoRepository cursoRepository;

  @Override
  public List<Curso> find() {
    return cursoRepository.findAll();
  }

  @Override
  public Optional<Curso> findBy(Integer id) {
    return cursoRepository.findById(id);
  }

  @Override
  public Curso create(Curso curso) {
    return cursoRepository.save(curso);
  }

  @Override
  public Curso update(Integer id, Curso cursoUpdate) {
    Optional<Curso> optionalCurso = cursoRepository.findById(id);
    Curso curso = optionalCurso.get();

    curso.setTitulo(cursoUpdate.getTitulo());
    curso.setDescricao(cursoUpdate.getDescricao());
    curso.setSituacao(cursoUpdate.getSituacao());

    return cursoRepository.save(curso);
  }

  @Override
  public void delete(Integer id) {
    Optional<Curso> optionalCurso = cursoRepository.findById(id);
    Curso curso = optionalCurso.get();

    cursoRepository.delete(curso);
  }

}
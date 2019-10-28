package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoBusinessImpl implements CursoBusiness {

  @Autowired
  private CursoRepository cursoRepository;

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

}
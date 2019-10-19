package br.com.unifacef.escola.business;

import br.com.unifacef.escola.model.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoBusiness {
  
  List<Curso> find();
  Optional<Curso> findBy(Integer id);
  Curso create(Curso curso);
  Curso update(Integer id, Curso curso);
  void delete(Integer id);
  
}
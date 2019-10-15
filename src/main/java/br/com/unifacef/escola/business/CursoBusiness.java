package br.com.unifacef.escola.business;

import java.util.List;
import java.util.Optional;

import br.com.unifacef.escola.model.Curso;

public interface CursoBusiness {
  
  List<Curso> find();
  Optional<Curso> findBy(Integer id);
  Curso create(Curso curso);
  Curso update(Integer id, Curso curso);
  void delete(Integer id);
  
}
package br.com.unifacef.escola.business;

import br.com.unifacef.escola.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CursoBusiness {
  
  Page<Curso> find(Pageable pageable);
  Curso findBy(Integer id);
  Curso create(Curso curso);
  Curso update(Integer id, Curso curso);
  void delete(Integer id);
  
}
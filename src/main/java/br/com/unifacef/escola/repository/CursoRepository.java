package br.com.unifacef.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unifacef.escola.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> { }
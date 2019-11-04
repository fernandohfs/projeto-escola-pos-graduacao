package br.com.unifacef.escola.repository;

import br.com.unifacef.escola.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Page<Aluno> findByNomeContaining(String nome, Pageable pageable);

}

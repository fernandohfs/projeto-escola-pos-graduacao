package br.com.unifacef.escola.repository;

import br.com.unifacef.escola.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TurmaRepository extends JpaRepository<Turma, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE turma SET curso_id = null WHERE curso_id = ?", nativeQuery = true)
    public void detachAll(Integer id);

}

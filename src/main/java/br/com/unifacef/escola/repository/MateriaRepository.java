package br.com.unifacef.escola.repository;

import br.com.unifacef.escola.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE materia SET professor_id = null WHERE professor_id = ?", nativeQuery = true)
    public void detachAll(Integer id);

}

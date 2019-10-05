package br.com.unifacef.escola.business;

import br.com.unifacef.escola.model.Aluno;

import java.util.List;
import java.util.Optional;

public interface AlunoBusiness {

    List<Aluno> find();
    Optional<Aluno> findBy(Integer id);
    Aluno update(Integer id, Aluno aluno);
    void delete(Integer id);

}

package br.com.unifacef.escola.business;

import br.com.unifacef.escola.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlunoBusiness {

    Page<Aluno> find(Pageable pageable);
    Aluno findBy(Integer id);
    Aluno create(Aluno aluno);
    Aluno update(Integer id, Aluno aluno);
    void delete(Integer id);

}

package br.com.unifacef.escola.business;

import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TurmaBusiness {

    public Page<Turma> find(Pageable pageable);
    public Turma findById(Integer id);
    public Turma create(Turma turma);
    public Turma update(Integer id, Turma turmaUpdate);
    public void delete(Integer id);
    //Curso
    public Turma attach(Integer id, Curso curso);
    public List<Turma> sync(List<Turma> turmas, Curso curso);
    public void detach(Integer id);
    public void detachAll(Integer id);

}

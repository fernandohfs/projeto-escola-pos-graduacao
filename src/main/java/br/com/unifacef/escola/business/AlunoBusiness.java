package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.validation.turma.TurmaFlexibleValidation;
import br.com.unifacef.escola.model.Aluno;
import br.com.unifacef.escola.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlunoBusiness {

    Page<Aluno> find(Pageable pageable);
    Aluno findBy(Integer id);
    Aluno create(Aluno aluno);
    Aluno update(Integer id, Aluno aluno);
    void delete(Integer id);
    public List<Turma> attachTurma(Integer idAluno, List<TurmaFlexibleValidation> alunoTurmas);
    public List<Turma> attachTurma(Integer idAluno, Integer idTurma);
    public List<Turma> syncTurmas(Integer idAluno, List<TurmaFlexibleValidation> alunoTurmas);
    public void detach(Integer idAluno, Integer idTurma);
    public void detach(Integer idAluno, List<TurmaFlexibleValidation> alunoTurmas);
    public void detachAll(Integer idAluno);

}

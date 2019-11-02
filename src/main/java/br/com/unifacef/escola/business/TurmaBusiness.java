package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.validation.aluno.AlunoFlexibleValidation;
import br.com.unifacef.escola.model.Aluno;
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
    //Aluno
    public List<Aluno> attachAluno(Integer idTurma, List<AlunoFlexibleValidation> turmaAlunos);
    public List<Aluno> attachAluno(Integer idTurma, Integer idAluno);
    public List<Aluno> syncAlunos(Integer idTurma, List<AlunoFlexibleValidation> turmaAlunos);
    public void detachAluno(Integer idTurma, Integer idAluno);
    public void detachAluno(Integer idTurma, List<AlunoFlexibleValidation> turmaAlunos);
    public void detachAllAluno(Integer idTurma);

}

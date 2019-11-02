package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.AlunoBusiness;
import br.com.unifacef.escola.business.TurmaBusiness;
import br.com.unifacef.escola.contract.validation.aluno.AlunoFlexibleValidation;
import br.com.unifacef.escola.model.Aluno;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Turma;
import br.com.unifacef.escola.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaBusinessImpl implements TurmaBusiness {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoBusiness alunoBusiness;

    @Override
    public Page<Turma> find(Pageable pageable) {
        return turmaRepository.findAll(pageable);
    }

    @Override
    public Turma findById(Integer id) {
        return turmaRepository.getOne(id);
    }

    @Override
    public Turma create(Turma turma) {
        return turmaRepository.save(turma);
    }

    @Override
    public Turma update(Integer id, Turma turmaUpdate) {
        Turma turma = turmaRepository.getOne(id);
        turma.update(turmaUpdate);
        return turmaRepository.save(turma);
    }

    @Override
    public void delete(Integer id) {
        turmaRepository.deleteById(id);
    }

    /******
     CURSO
     ******/

    @Override
    public Turma attach(Integer id, Curso curso) {
        Turma turma = turmaRepository.getOne(id);
        turma.setCurso(curso);
        return turmaRepository.save(turma);
    }

    @Override
    public List<Turma> sync(List<Turma> turmas, Curso curso) {
        detachAll(curso.getId());

        turmas.forEach(turma -> {
            attach(turma.getId(), curso);
        });

        return turmas;
    }

    @Override
    public void detach(Integer id) {
        Turma turma = turmaRepository.getOne(id);
        turma.setCurso(null);
        turmaRepository.save(turma);
    }

    @Override
    public void detachAll(Integer id) {
        turmaRepository.detachAll(id);
    }

    /******
     ALUNO
     ******/

    @Override
    public List<Aluno> attachAluno(Integer idTurma, List<AlunoFlexibleValidation> turmaAlunos) {
        Turma turma = findById(idTurma);
        saveAttachAluno(turma, turmaAlunos);
        return turma.getAlunos();
    }

    @Override
    public List<Aluno> attachAluno(Integer idTurma, Integer idAluno) {
        Turma turma = findById(idTurma);
        Aluno aluno = alunoBusiness.findBy(idAluno);
        saveAttachAluno(turma, aluno);
        return turma.getAlunos();
    }

    @Override
    public List<Aluno> syncAlunos(Integer idTurma, List<AlunoFlexibleValidation> turmaAlunos) {
        detachAllAluno(idTurma);
        return attachAluno(idTurma, turmaAlunos);
    }

    @Override
    public void detachAluno(Integer idTurma, Integer idAluno) {
        Turma turma = findById(idTurma);
        List<Aluno> alunos = turma.getAlunos().stream().filter(aluno -> aluno.getId() != idAluno).collect(Collectors.toList());
        turma.setAlunos(alunos);
        turmaRepository.save(turma);
    }

    @Override
    public void detachAluno(Integer idTurma, List<AlunoFlexibleValidation> turmaAlunos) {
        Turma turma = findById(idTurma);

        turmaAlunos.forEach(aluno -> {
            turma.setAlunos(
                    turma.getAlunos().stream().filter(a -> a.getId() != aluno.getId()).collect(Collectors.toList())
            );
        });

        turmaRepository.save(turma);
    }

    @Override
    public void detachAllAluno(Integer idTurma) {
        Turma turma = findById(idTurma);
        turma.setAlunos(new ArrayList<Aluno>());
        turmaRepository.save(turma);
    }

    private void saveAttachAluno(Turma turma, List<AlunoFlexibleValidation> turmaAlunos) {
        List<Aluno> alunos = turma.getAlunos();
        turmaAlunos.forEach(aluno -> {
            alunos.add(aluno.converter());
        });

        turma.setAlunos(alunos);
        turmaRepository.save(turma);
    }

    private void saveAttachAluno(Turma turma,  Aluno aluno) {
        List<Aluno> alunos = turma.getAlunos();
        alunos.add(aluno);

        turma.setAlunos(alunos);
        turmaRepository.save(turma);
    }

}

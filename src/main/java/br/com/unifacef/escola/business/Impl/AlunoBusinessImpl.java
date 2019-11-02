package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.AlunoBusiness;
import br.com.unifacef.escola.business.TurmaBusiness;
import br.com.unifacef.escola.contract.validation.turma.TurmaFlexibleValidation;
import br.com.unifacef.escola.model.Aluno;
import br.com.unifacef.escola.model.Turma;
import br.com.unifacef.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoBusinessImpl implements AlunoBusiness {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaBusiness turmaBusiness;

    @Override
    public Page<Aluno> find(Pageable pageable) {
        return alunoRepository.findAll(pageable);
    }

    @Override
    public Aluno findBy(Integer id) {
        return alunoRepository.getOne(id);
    }

    @Override
    public Aluno create(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno update(Integer alunoId, Aluno alunoUpdate) {
        Aluno aluno = alunoRepository.getOne(alunoId);
        aluno.update(alunoUpdate);
        return alunoRepository.save(aluno);
    }

    @Override
    public void delete(Integer alunoId) {
        alunoRepository.deleteById(alunoId);
    }

    @Override
    public List<Turma> attachTurma(Integer idAluno, List<TurmaFlexibleValidation> alunoTurmas) {
        Aluno aluno = findBy(idAluno);
        saveAttachTurma(aluno, alunoTurmas);
        return aluno.getTurmas();
    }

    @Override
    public List<Turma> attachTurma(Integer idAluno, Integer idTurma) {
        Aluno aluno = findBy(idAluno);
        Turma turma = turmaBusiness.findById(idTurma);
        saveAttachTurma(aluno, turma);
        return aluno.getTurmas();
    }

    @Override
    public List<Turma> syncTurmas(Integer idAluno, List<TurmaFlexibleValidation> alunoTurmas) {
        detachAll(idAluno);
        return attachTurma(idAluno, alunoTurmas);
    }

    @Override
    public void detach(Integer idAluno, Integer idTurma) {
        Aluno aluno = findBy(idAluno);
        List<Turma> turmas = aluno.getTurmas().stream().filter(turma -> turma.getId() != idTurma).collect(Collectors.toList());
        aluno.setTurmas(turmas);
        alunoRepository.save(aluno);
    }

    @Override
    public void detach(Integer idAluno, List<TurmaFlexibleValidation> alunoTurmas) {
        Aluno aluno = findBy(idAluno);

        alunoTurmas.forEach(turma -> {
            aluno.setTurmas(
                    aluno.getTurmas().stream().filter(a -> a.getId() != turma.getId()).collect(Collectors.toList())
            );
        });

        alunoRepository.save(aluno);
    }

    @Override
    public void detachAll(Integer idAluno) {
        Aluno aluno = findBy(idAluno);
        aluno.setTurmas(new ArrayList<Turma>());
        alunoRepository.save(aluno);
    }

    private void saveAttachTurma(Aluno aluno, List<TurmaFlexibleValidation> alunoTurmas) {
        List<Turma> turmas = aluno.getTurmas();
        alunoTurmas.forEach(turma -> {
            turmas.add(turma.converter());
        });

        aluno.setTurmas(turmas);
        alunoRepository.save(aluno);
    }

    private void saveAttachTurma(Aluno aluno,  Turma turma) {
        List<Turma> turmas = aluno.getTurmas();
        turmas.add(turma);

        aluno.setTurmas(turmas);
        alunoRepository.save(aluno);
    }

}

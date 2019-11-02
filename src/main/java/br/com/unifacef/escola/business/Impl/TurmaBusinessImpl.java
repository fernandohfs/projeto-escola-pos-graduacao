package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.TurmaBusiness;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Turma;
import br.com.unifacef.escola.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaBusinessImpl implements TurmaBusiness {

    @Autowired
    private TurmaRepository turmaRepository;

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

}

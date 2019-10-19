package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.AlunoBusiness;
import br.com.unifacef.escola.model.Aluno;
import br.com.unifacef.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoBusinessImpl implements AlunoBusiness {

    @Autowired
    private AlunoRepository alunoRepository;

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

}

package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.AlunoBusiness;
import br.com.unifacef.escola.model.Aluno;
import br.com.unifacef.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoBusinessImpl implements AlunoBusiness {

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public List<Aluno> find() {
        return alunoRepository.findAll();
    }

    @Override
    public Optional<Aluno> findBy(Integer id) {
        return alunoRepository.findById(id);
    }

    @Override
    public Aluno create(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Override
    public Aluno update(Integer alunoId, Aluno alunoUpdate) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(alunoId);
        Aluno aluno = optionalAluno.get();

        aluno.setNome(alunoUpdate.getNome());
        aluno.setEmail(alunoUpdate.getEmail());
        aluno.setDataNascimento(alunoUpdate.getDataNascimento());
        aluno.setCpf(alunoUpdate.getCpf());

        return alunoRepository.save(aluno);
    }

    @Override
    public void delete(Integer alunoId) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(alunoId);
        Aluno aluno = optionalAluno.get();

        alunoRepository.delete(aluno);
    }

}

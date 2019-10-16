package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.ProfessorBusiness;
import br.com.unifacef.escola.contract.returnJson.professor.ProfessorReturn;
import br.com.unifacef.escola.model.Professor;
import br.com.unifacef.escola.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorBusinessImpl implements ProfessorBusiness {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<ProfessorReturn> find() {
        return ProfessorReturn.parse(professorRepository.findAll());
    }

    @Override
    public ProfessorReturn findBy(Integer id) {
        return ProfessorReturn.parse(professorRepository.getOne(id));
    }

    @Override
    public ProfessorReturn create(Professor professor) {
        return ProfessorReturn.parse(professorRepository.save(professor));
    }

    @Override
    public ProfessorReturn update(Integer id, Professor professorUpdate) {
        Professor professor = professorRepository.getOne(id);
        professor.update(professorUpdate);
        return ProfessorReturn.parse(professorRepository.save(professor));
    }

    @Override
    public void delete(Integer id) {
        professorRepository.deleteById(id);
    }

}

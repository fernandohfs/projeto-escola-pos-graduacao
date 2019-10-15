package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.ProfessorBusiness;
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
    public List<Professor> find() {
        return professorRepository.findAll();
    }

    @Override
    public Professor findBy(Integer id) {
        return professorRepository.getOne(id);
    }

    @Override
    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor update(Integer id, Professor professorUpdate) {
        Professor professor = professorRepository.getOne(id);
        professor.update(professorUpdate);
        return professorRepository.save(professor);
    }

    @Override
    public void delete(Integer id) {
        professorRepository.deleteById(id);
    }

}

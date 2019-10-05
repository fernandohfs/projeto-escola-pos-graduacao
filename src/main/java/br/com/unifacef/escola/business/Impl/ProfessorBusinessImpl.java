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

    private ProfessorRepository professorRepository;

    @Autowired
    public ProfessorBusinessImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor update(Integer id, Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> findBy(Integer id) {
        List professor = new ArrayList<Professor>();
        professor.add(professorRepository.findById(id).get());
        return professor;
    }

}

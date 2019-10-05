package br.com.unifacef.escola.business;

import br.com.unifacef.escola.model.Professor;

import java.util.List;

public interface ProfessorBusiness {
    public List<Professor> findBy(Integer id);
    public List<Professor> findAll();
    public Professor create(Professor professor);
    public Professor update(Integer id, Professor professor);
}

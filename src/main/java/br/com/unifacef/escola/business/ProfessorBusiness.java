package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.returnJson.professor.ProfessorReturn;
import br.com.unifacef.escola.model.Professor;

import java.util.List;

public interface ProfessorBusiness {

    public List<ProfessorReturn> find();
    public ProfessorReturn findBy(Integer id);
    public ProfessorReturn create(Professor professor);
    public ProfessorReturn update(Integer id, Professor professor);
    public void delete(Integer id);

}

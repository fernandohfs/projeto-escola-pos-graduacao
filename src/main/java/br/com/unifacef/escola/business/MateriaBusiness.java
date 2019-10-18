package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.response.materia.MateriaResponse;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;

import java.util.List;

public interface MateriaBusiness {

    public List<MateriaResponse> find();
    public MateriaResponse findById(Integer id);
    public MateriaResponse create(Materia materia);
    public MateriaResponse update(Integer id, Materia materiaUpdate);
    public void delete(Integer id);
    public Materia attach(Integer id, Professor professor);
    public List<Materia> sync(List<Materia> materias, Professor professor);
    public void detach(Integer id);
    public void detachAll(Integer id);

}

package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.response.materia.MateriaResponse;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MateriaBusiness {

    public Page<Materia> find(Pageable pageable);
    public Materia findById(Integer id);
    public Materia create(Materia materia);
    public Materia update(Integer id, Materia materiaUpdate);
    public void delete(Integer id);
    public Materia attach(Integer id, Professor professor);
    public List<Materia> sync(List<Materia> materias, Professor professor);
    public void detach(Integer id);
    public void detachAll(Integer id);

}

package br.com.unifacef.escola.business;

import br.com.unifacef.escola.model.Materia;

import java.util.List;

public interface MateriaBusiness {

    public List<Materia> find();
    public Materia findById(Integer id);
    public Materia create(Materia materia);
    public Materia update(Integer id, Materia materiaUpdate);
    public void delete(Integer id);

}

package br.com.unifacef.escola.business;

import br.com.unifacef.escola.contract.returnJson.materia.MateriaReturn;
import br.com.unifacef.escola.model.Materia;

import java.util.List;

public interface MateriaBusiness {

    public List<MateriaReturn> find();
    public MateriaReturn findById(Integer id);
    public MateriaReturn create(Materia materia);
    public MateriaReturn update(Integer id, Materia materiaUpdate);
    public void delete(Integer id);

}

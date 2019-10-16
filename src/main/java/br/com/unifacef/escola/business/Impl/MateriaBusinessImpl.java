package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.contract.returnJson.materia.MateriaReturn;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaBusinessImpl implements MateriaBusiness {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<MateriaReturn> find() {
        return MateriaReturn.parse(materiaRepository.findAll());
    }

    @Override
    public MateriaReturn findById(Integer id) {
        return MateriaReturn.parse(materiaRepository.getOne(id));
    }

    @Override
    public MateriaReturn create(Materia materia) {
        return MateriaReturn.parse(materiaRepository.save(materia));
    }

    @Override
    public MateriaReturn update(Integer id, Materia materiaUpdate) {
        Materia materia = materiaRepository.getOne(id);
        materia.update(materiaUpdate);
        return MateriaReturn.parse(materiaRepository.save(materia));
    }

    @Override
    public void delete(Integer id) {
        materiaRepository.deleteById(id);
    }
}

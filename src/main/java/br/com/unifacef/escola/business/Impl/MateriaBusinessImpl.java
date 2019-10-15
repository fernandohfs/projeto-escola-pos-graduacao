package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.MateriaBusiness;
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
    public List<Materia> find() {
        return materiaRepository.findAll();
    }

    @Override
    public Materia findById(Integer id) {
        return materiaRepository.getOne(id);
    }

    @Override
    public Materia create(Materia materia) {
        return materiaRepository.save(materia);
    }

    @Override
    public Materia update(Integer id, Materia materiaUpdate) {
        Materia materia = materiaRepository.getOne(id);
        materia.update(materiaUpdate);
        return materiaRepository.save(materia);
    }

    @Override
    public void delete(Integer id) {
        materiaRepository.deleteById(id);
    }
}

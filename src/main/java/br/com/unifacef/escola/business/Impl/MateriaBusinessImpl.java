package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.contract.response.materia.MateriaResponse;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import br.com.unifacef.escola.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaBusinessImpl implements MateriaBusiness {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public Page<Materia> find(Pageable pageable) {
        return materiaRepository.findAll(pageable);
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

    @Override
    public Materia attach(Integer id, Professor professor) {
        Materia materia = materiaRepository.getOne(id);
        materia.setProfessor(professor);
        return materiaRepository.save(materia);
    }

    @Override
    public List<Materia> sync(List<Materia> materias, Professor professor) {
        detachAll(professor.getId());

        materias.forEach(materia -> {
            attach(materia.getId(), professor);
        });

        return materias;
    }

    @Override
    public void detach(Integer id) {
        Materia materia = materiaRepository.getOne(id);
        materia.setProfessor(null);
        materiaRepository.save(materia);
    }

    @Override
    public void detachAll(Integer id) {
        materiaRepository.detachAll(id);
    }

}

package br.com.unifacef.escola.business.Impl;

import br.com.unifacef.escola.business.CursoBusiness;
import br.com.unifacef.escola.business.MateriaBusiness;
import br.com.unifacef.escola.contract.validation.curso.CursoFlexibleValidation;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import br.com.unifacef.escola.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaBusinessImpl implements MateriaBusiness {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private CursoBusiness cursoBusiness;

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

    /******
     PROFESSOR
     ******/

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

    /******
      CURSO
     ******/

    @Override
    public List<Curso> attachCurso(Integer idMateria, List<CursoFlexibleValidation> materiaCursos) {
        Materia materia = findById(idMateria);
        saveAttachCurso(materia, materiaCursos);
        return materia.getCursos();
    }

    @Override
    public List<Curso> attachCurso(Integer idMateria, Integer idCurso) {
        Materia materia = findById(idMateria);
        Curso curso = cursoBusiness.findBy(idCurso);
        saveAttachCurso(materia, curso);
        return materia.getCursos();
    }

    private void saveAttachCurso(Materia materia, List<CursoFlexibleValidation> materiaCursos) {
        List<Curso> cursos = materia.getCursos();
        materiaCursos.forEach(curso -> {
            cursos.add(curso.converter());
        });

        materia.setCursos(cursos);
        materiaRepository.save(materia);
    }

    private void saveAttachCurso(Materia materia,  Curso curso) {
        List<Curso> cursos = materia.getCursos();
        cursos.add(curso);

       materia.setCursos(cursos);
       materiaRepository.save(materia);
    }

    @Override
    public List<Curso> syncCursos(Integer idMateria, List<CursoFlexibleValidation> materiaCursos) {
        detachAllCurso(idMateria);
        return attachCurso(idMateria, materiaCursos);
    }

    @Override
    public void detachCurso(Integer idMateria, Integer idCurso) {
        Materia materia = findById(idMateria);
        List<Curso> cursos = materia.getCursos().stream().filter(curso -> curso.getId() != idCurso).collect(Collectors.toList());
        materia.setCursos(cursos);
        materiaRepository.save(materia);
    }

    @Override
    public void detachCurso(Integer idMateria, List<CursoFlexibleValidation> materiaCursos) {
        Materia materia = findById(idMateria);

        materiaCursos.forEach(curso -> {
            materia.setCursos(
                    materia.getCursos().stream().filter(c -> c.getId() != curso.getId()).collect(Collectors.toList())
            );
        });

        materiaRepository.save(materia);
    }

    @Override
    public void detachAllCurso(Integer idMateria) {
        Materia materia = findById(idMateria);
        materia.setCursos(new ArrayList<Curso>());
        materiaRepository.save(materia);
    }

}

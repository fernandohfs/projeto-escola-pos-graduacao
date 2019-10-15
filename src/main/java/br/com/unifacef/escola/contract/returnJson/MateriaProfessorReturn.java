package br.com.unifacef.escola.contract.returnJson;

import br.com.unifacef.escola.model.Professor;

public class MateriaProfessorReturn {

    private Integer id;
    private String Nome;

    public MateriaProfessorReturn(Integer id, String nome) {
        this.id = id;
        Nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public static MateriaProfessorReturn parser(Professor professor) {
        if (professor != null) {
            return new MateriaProfessorReturn(
                    professor.getId(),
                    professor.getNome()
            );
        }

        return null;
    }

}

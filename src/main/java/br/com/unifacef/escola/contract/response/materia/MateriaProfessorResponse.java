package br.com.unifacef.escola.contract.response.materia;

import br.com.unifacef.escola.model.Professor;

public class MateriaProfessorResponse {

    private Integer id;
    private String Nome;

    public MateriaProfessorResponse(Integer id, String nome) {
        this.id = id;
        Nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public static MateriaProfessorResponse parser(Professor professor) {
        if (professor != null) {
            return new MateriaProfessorResponse(
                    professor.getId(),
                    professor.getNome()
            );
        }

        return null;
    }

}

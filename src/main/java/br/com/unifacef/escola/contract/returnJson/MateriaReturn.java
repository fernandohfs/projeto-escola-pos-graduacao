package br.com.unifacef.escola.contract.returnJson;

import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MateriaReturn {

    private Integer id;
    private String titulo;
    private String descricao;
    private MateriaProfessorReturn professor;
    private char situacao;

    public MateriaReturn(Integer id, String titulo, String descricao, Professor professor, char situacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.professor = MateriaProfessorReturn.parser(professor);
        this.situacao = situacao;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public MateriaProfessorReturn getProfessor() {
        return professor;
    }

    public char getSituacao() {
        return situacao;
    }

    public static MateriaReturn parse(Materia materia) {
        return new MateriaReturn(
                materia.getId(),
                materia.getTitulo(),
                materia.getDescricao(),
                materia.getProfessor(),
                materia.getSituacao()
        );
    }

    public static List<MateriaReturn> parse(List<Materia> materias) {
        List<MateriaReturn> materiasJson = new ArrayList<>();

        for (Materia materia : materias) {
            materiasJson.add(MateriaReturn.parse(materia));
        }

        return materiasJson;
    }

}

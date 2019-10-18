package br.com.unifacef.escola.contract.response.materia;

import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MateriaResponse {

    private Integer id;
    private String titulo;
    private String descricao;
    private MateriaProfessorResponse professor;
    private char situacao;

    public MateriaResponse(Integer id, String titulo, String descricao, Professor professor, char situacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.professor = MateriaProfessorResponse.parser(professor);
        this.situacao = situacao;
    }

    public static MateriaResponse parse(Materia materia) {
        return new MateriaResponse(
                materia.getId(),
                materia.getTitulo(),
                materia.getDescricao(),
                materia.getProfessor(),
                materia.getSituacao()
        );
    }

    public static List<MateriaResponse> parse(List<Materia> materias) {
        List<MateriaResponse> materiasJson = new ArrayList<>();

        for (Materia materia : materias) {
            materiasJson.add(MateriaResponse.parse(materia));
        }

        return materiasJson;
    }

}

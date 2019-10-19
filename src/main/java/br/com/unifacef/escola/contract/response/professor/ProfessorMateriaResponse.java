package br.com.unifacef.escola.contract.response.professor;

import br.com.unifacef.escola.model.Materia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorMateriaResponse {

    private Integer id;
    private String titulo;
    private String descricao;
    private char situacao;

    public static ProfessorMateriaResponse parse(Materia materia) {
        return new ProfessorMateriaResponse(
                materia.getId(),
                materia.getTitulo(),
                materia.getDescricao(),
                materia.getSituacao()
        );
    }

    public static List<ProfessorMateriaResponse> parse(List<Materia> materias) {
        List<ProfessorMateriaResponse> materiasResponse = new ArrayList<>();
        materias.forEach(materia -> materiasResponse.add(ProfessorMateriaResponse.parse(materia)));
        return materiasResponse;
    }

}

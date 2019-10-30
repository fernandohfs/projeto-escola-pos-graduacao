package br.com.unifacef.escola.contract.response.materia;

import br.com.unifacef.escola.model.Materia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleMateriaResponse {

    private Integer id;
    private String titulo;
    private String descricao;
    private char situacao;

    public static SimpleMateriaResponse parse(Materia materia) {
        return new SimpleMateriaResponse(
                materia.getId(),
                materia.getTitulo(),
                materia.getDescricao(),
                materia.getSituacao()
        );
    }

    public static List<SimpleMateriaResponse> parse(List<Materia> materias) {
        List<SimpleMateriaResponse> materiasResponse = new ArrayList<>();
        materias.forEach(materia -> materiasResponse.add(SimpleMateriaResponse.parse(materia)));
        return materiasResponse;
    }

}

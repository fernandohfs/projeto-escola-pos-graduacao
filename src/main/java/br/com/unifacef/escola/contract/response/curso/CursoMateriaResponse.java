package br.com.unifacef.escola.contract.response.curso;

import br.com.unifacef.escola.model.Materia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursoMateriaResponse {

    private Integer id;
    private String titulo;
    private String descricao;
    private char situacao;

    public static CursoMateriaResponse parse(Materia materia) {
        return new CursoMateriaResponse(
                materia.getId(),
                materia.getTitulo(),
                materia.getDescricao(),
                materia.getSituacao()
        );
    }

    public static List<CursoMateriaResponse> parse(List<Materia> materias) {
        return materias.stream().map(CursoMateriaResponse::parse).collect(Collectors.toList());
    }

}

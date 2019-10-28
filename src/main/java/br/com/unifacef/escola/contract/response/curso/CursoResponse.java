package br.com.unifacef.escola.contract.response.curso;

import br.com.unifacef.escola.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoResponse {

    private Integer id;
    private String titulo;
    private String descricao;
    private Character situacao;

    public static CursoResponse parse(Curso curso) {
        return new CursoResponse(
                curso.getId(),
                curso.getTitulo(),
                curso.getDescricao(),
                curso.getSituacao()
        );
    }

    public static List<CursoResponse> parse(List<Curso> cursos) {
        return cursos.stream().map(CursoResponse::parse).collect(Collectors.toList());
    }

}

package br.com.unifacef.escola.contract.response.turma;

import br.com.unifacef.escola.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TurmaCursoResponse {

    private Integer id;
    private String titulo;
    private String descricao;

    public static TurmaCursoResponse parser(Curso curso) {
        if (curso != null) {
            return new TurmaCursoResponse(
                    curso.getId(),
                    curso.getTitulo(),
                    curso.getDescricao()
            );
        }

        return null;
    }

}

package br.com.unifacef.escola.contract.validation.curso;

import br.com.unifacef.escola.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoFlexibleValidation {

    private Integer id;
    private String titulo;
    private String descricao;
    private Character situacao;

    public Curso converter() {
        return Curso.builder()
                .id(getId())
                .titulo(getTitulo())
                .descricao(getDescricao())
                .situacao(getSituacao())
                .build();
    }

}

package br.com.unifacef.escola.contract.validation.curso;

import br.com.unifacef.escola.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoValidation {

    private Integer id;
    @NotNull @Size(min = 5, max = 250)
    private String titulo;
    @NotNull @Size(max = 255)
    private String descricao;
    @NotNull
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

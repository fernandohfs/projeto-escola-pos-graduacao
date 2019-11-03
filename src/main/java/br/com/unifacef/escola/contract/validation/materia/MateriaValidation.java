package br.com.unifacef.escola.contract.validation.materia;

import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MateriaValidation {

    private Integer id;
    @NotNull @Size(min = 5, max = 150)
    private String titulo;
    @NotNull @Size(min = 5, max = 150)
    private String descricao;
    @NotNull
    private char situacao;
    private Professor professor;

    public Materia converter() {
        Materia materia = new Materia();

        materia.setId(getId());
        materia.setTitulo(getTitulo());
        materia.setDescricao(getDescricao());
        materia.setSituacao(getSituacao());
        materia.setProfessor(getProfessor());

        return materia;
    }

}

package br.com.unifacef.escola.contract.validation.materia;

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
public class MateriaFlexibleValidation {

    @NotNull
    private Integer id;
    private String titulo;
    private String descricao;
    private char situacao;

    public Materia converter() {
        Materia materia = new Materia();

        materia.setId(getId());
        materia.setTitulo(getTitulo());
        materia.setDescricao(getDescricao());
        materia.setSituacao(getSituacao());

        return materia;
    }

    public static List<Materia> converterList(List<MateriaFlexibleValidation> professorMaterias) {
        List<Materia> materias = new ArrayList<>();
        professorMaterias.forEach(materia -> {
            materias.add(materia.converter());
        });
        return materias;
    }

}

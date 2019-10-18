package br.com.unifacef.escola.contract.validation;

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
public class ProfessorMateriaValidation {

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

    public static List<Materia> converterList(List<ProfessorMateriaValidation> professorMaterias) {
        List<Materia> materias = new ArrayList<>();
        professorMaterias.forEach(materia -> {
            materias.add(materia.converter());
        });
        return materias;
    }

}

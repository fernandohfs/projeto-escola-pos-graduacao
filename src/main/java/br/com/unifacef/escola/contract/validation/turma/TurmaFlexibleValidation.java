package br.com.unifacef.escola.contract.validation.turma;

import br.com.unifacef.escola.contract.validation.materia.MateriaFlexibleValidation;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurmaFlexibleValidation {

    @NotNull
    private Integer id;
    private LocalDate dataPeriodoInicio;
    private LocalDate dataPeriodoFim;
    private String observacao;
    private Character situacao;

    public Turma converter() {
        Turma turma = new Turma();

        turma.setId(getId());
        turma.setDataPeriodoInicio(getDataPeriodoInicio());
        turma.setDataPeriodoFim(getDataPeriodoFim());
        turma.setObservacao(getObservacao());
        turma.setSituacao(getSituacao());

        return turma;
    }

    public static List<Turma> converterList(List<TurmaFlexibleValidation> cursoTurmas) {
        List<Turma> turmas = new ArrayList<>();
        cursoTurmas.forEach(turma -> {
            turmas.add(turma.converter());
        });
        return turmas;
    }

}

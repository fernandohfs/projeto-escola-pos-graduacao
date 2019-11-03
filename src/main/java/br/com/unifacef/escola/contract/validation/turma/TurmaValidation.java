package br.com.unifacef.escola.contract.validation.turma;

import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Turma;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurmaValidation {

    private Integer id;
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPeriodoInicio;
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPeriodoFim;
    @NotNull @Size(min = 5, max = 255)
    private String observacao;
    @NotNull
    private Character situacao;
    private Curso curso;

    public Turma converter() {
        Turma turma = new Turma();

        turma.setId(getId());
        turma.setDataPeriodoInicio(getDataPeriodoInicio());
        turma.setDataPeriodoFim(getDataPeriodoFim());
        turma.setObservacao(getObservacao());
        turma.setSituacao(getSituacao());
        turma.setCurso(getCurso());

        return turma;
    }
}

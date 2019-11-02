package br.com.unifacef.escola.contract.response.turma;

import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurmaResponse {

    private Integer id;
    private LocalDate dataPeriodoInicio;
    private LocalDate dataPeriodoFim;
    private String observacao;
    private Character situacao;
    private TurmaCursoResponse turmaCursoResponse;

    public TurmaResponse(Integer id, LocalDate dataPeriodoInicio, LocalDate dataPeriodoFim, String observacao,
                         Character situacao, Curso curso) {
        this.id = id;
        this.dataPeriodoInicio = dataPeriodoInicio;
        this.dataPeriodoFim = dataPeriodoFim;
        this.observacao = observacao;
        this.situacao = situacao;
        this.turmaCursoResponse = TurmaCursoResponse.parser(curso);
    }

    public static TurmaResponse parse(Turma turma) {
        return new TurmaResponse(
                turma.getId(),
                turma.getDataPeriodoInicio(),
                turma.getDataPeriodoFim(),
                turma.getObservacao(),
                turma.getSituacao(),
                turma.getCurso()
        );
    }

    public static List<TurmaResponse> parse(List<Turma> turmas) {
        List<TurmaResponse> turmasJson = new ArrayList<>();

        for (Turma turma : turmas) {
            turmasJson.add(TurmaResponse.parse(turma));
        }

        return turmasJson;
    }

}

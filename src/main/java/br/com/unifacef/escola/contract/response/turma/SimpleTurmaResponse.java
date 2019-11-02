package br.com.unifacef.escola.contract.response.turma;

import br.com.unifacef.escola.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTurmaResponse {

    private Integer id;
    private LocalDate dataPeriodoInicio;
    private LocalDate dataPeriodoFim;
    private String observacao;
    private Character situacao;

    public static SimpleTurmaResponse parse(Turma turma) {
        return new SimpleTurmaResponse(
                turma.getId(),
                turma.getDataPeriodoInicio(),
                turma.getDataPeriodoFim(),
                turma.getObservacao(),
                turma.getSituacao()
        );
    }

    public static List<SimpleTurmaResponse> parse(List<Turma> turmas) {
        List<SimpleTurmaResponse> turmasResponse = new ArrayList<>();
        turmas.forEach(turma -> turmasResponse.add(SimpleTurmaResponse.parse(turma)));
        return turmasResponse;
    }

}

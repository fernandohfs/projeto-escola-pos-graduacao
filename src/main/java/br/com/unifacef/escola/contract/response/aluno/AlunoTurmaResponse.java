package br.com.unifacef.escola.contract.response.aluno;

import br.com.unifacef.escola.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoTurmaResponse {

    private Integer id;
    private LocalDate dataPeriodoInicio;
    private LocalDate dataPeriodoFim;
    private String observacao;
    private Character situacao;

    public static AlunoTurmaResponse parse(Turma turma) {
        return new AlunoTurmaResponse(
                turma.getId(),
                turma.getDataPeriodoInicio(),
                turma.getDataPeriodoFim(),
                turma.getObservacao(),
                turma.getSituacao()
        );
    }

    public static List<AlunoTurmaResponse> parse(List<Turma> turmas) {
        return turmas.stream().map(AlunoTurmaResponse::parse).collect(Collectors.toList());
    }

}

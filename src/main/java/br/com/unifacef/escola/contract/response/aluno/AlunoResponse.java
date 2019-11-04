package br.com.unifacef.escola.contract.response.aluno;

import br.com.unifacef.escola.model.Aluno;
import br.com.unifacef.escola.model.Curso;
import br.com.unifacef.escola.model.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoResponse {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
    private List<Curso> cursos;

    public static AlunoResponse parse(Aluno aluno) {
        List<Turma> turmas = aluno.getTurmas();
        List<Curso> cursos = new ArrayList<>();

        if (turmas != null) {
            for (Turma turma : turmas) {
                cursos.add(turma.getCurso());
            }
        }

        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getDataNascimento(),
                aluno.getCpf(),
                cursos
        );
    }

    public static List<AlunoResponse> parse(List<Aluno> alunos) {
        return alunos.stream().map(AlunoResponse::parse).collect(Collectors.toList());
    }

}

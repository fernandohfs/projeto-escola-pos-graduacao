package br.com.unifacef.escola.contract.response.aluno;

import br.com.unifacef.escola.model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimpleAlunoResponse {
    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;

    public static SimpleAlunoResponse parse(Aluno aluno) {
        return new SimpleAlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getDataNascimento(),
                aluno.getCpf()
        );
    }

    public static List<SimpleAlunoResponse> parse(List<Aluno> alunos) {
        alunos.sort(Comparator.comparing(Aluno::getNome));

        return alunos.stream().map(SimpleAlunoResponse::parse).collect(Collectors.toList());
    }
}

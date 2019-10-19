package br.com.unifacef.escola.contract.response.aluno;

import br.com.unifacef.escola.model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    public static AlunoResponse parse(Aluno aluno) {
        return new AlunoResponse(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getDataNascimento(),
                aluno.getCpf()
        );
    }

    public static List<AlunoResponse> parse(List<Aluno> alunos) {
        return alunos.stream().map(AlunoResponse::parse).collect(Collectors.toList());
    }

}

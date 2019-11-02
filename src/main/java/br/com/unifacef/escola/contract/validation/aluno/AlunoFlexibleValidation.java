package br.com.unifacef.escola.contract.validation.aluno;

import br.com.unifacef.escola.model.Aluno;
import br.com.unifacef.escola.model.Curso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoFlexibleValidation {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;

    public Aluno converter() {
        return Aluno.builder()
                .id(getId())
                .nome(getNome())
                .email(getEmail())
                .dataNascimento(getDataNascimento())
                .cpf(getCpf())
                .build();
    }

}

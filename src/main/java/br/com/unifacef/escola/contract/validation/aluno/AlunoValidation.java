package br.com.unifacef.escola.contract.validation.aluno;

import br.com.unifacef.escola.model.Aluno;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoValidation {

    private Integer id;
    @NotNull @Size(min = 5, max = 150)
    private String nome;
    @NotNull @Email
    private String email;
    @NotNull @Past @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotNull @Size(min = 11, max = 11)
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

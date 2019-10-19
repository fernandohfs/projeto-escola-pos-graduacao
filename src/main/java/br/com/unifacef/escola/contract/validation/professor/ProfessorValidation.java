package br.com.unifacef.escola.contract.validation.professor;

import br.com.unifacef.escola.model.Professor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorValidation {

    private Integer id;
    @NotNull @Size(min=5, max = 150)
    private String nome;
    @NotNull
    private String email;
    @NotNull @Past @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
    @NotNull @Size(min=11, max=11)
    private String cpf;
    @NotNull @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataAdmissao;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataDemissao;

    public Professor converter() {
        Professor professor = new Professor();

        professor.setId(getId());
        professor.setNome(getNome());
        professor.setEmail(getEmail());
        professor.setDataNascimento(getDataNascimento());
        professor.setCpf(getCpf());
        professor.setDataAdmissao(getDataAdmissao());
        professor.setDataDemissao(getDataDemissao());

        return professor;
    }

}

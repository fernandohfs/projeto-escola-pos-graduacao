package br.com.unifacef.escola.contract.validation;

import br.com.unifacef.escola.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorValidation {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
    private Date dataAdmissao;
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

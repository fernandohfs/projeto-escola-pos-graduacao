package br.com.unifacef.escola.contract.response.professor;

import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Professor;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorResponse {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
    private Date dataAdmissao;
    private Date dataDemissao;

    public static ProfessorResponse parse(Professor professor) {
        return new ProfessorResponse(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDataNascimento(),
                professor.getCpf(),
                professor.getDataAdmissao(),
                professor.getDataDemissao()
        );
    }

    public static List<ProfessorResponse> parse(List<Professor> professores) {
        List<ProfessorResponse> professoresResponse = new ArrayList<>();
        professores.forEach(professor -> {
            professoresResponse.add(ProfessorResponse.parse(professor));
        });
        return professoresResponse;
    }

}

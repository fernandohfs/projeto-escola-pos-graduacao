package br.com.unifacef.escola.contract.returnJson.professor;

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
public class ProfessorReturn {

    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
    private Date dataAdmissao;
    private Date dataDemissao;

    public static ProfessorReturn parse(Professor professor) {
        return new ProfessorReturn(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDataNascimento(),
                professor.getCpf(),
                professor.getDataAdmissao(),
                professor.getDataDemissao()
        );
    }

    public static List<ProfessorReturn> parse(List<Professor> professores) {
        List<ProfessorReturn> professoresJson = new ArrayList<>();
        professores.forEach(materia -> professoresJson.add(ProfessorReturn.parse(materia)));
        return professoresJson;
    }

}

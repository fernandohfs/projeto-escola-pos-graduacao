package br.com.unifacef.escola.contract.response.professor;

import br.com.unifacef.escola.contract.response.general.MetaResponse;
import br.com.unifacef.escola.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorResponseList {

    private MetaResponse meta;
    private List<ProfessorResponse> content;

    public static  ProfessorResponseList parse(Page<Professor> pageProfessor) {
        MetaResponse meta = new MetaResponse(pageProfessor.getTotalElements(), pageProfessor.getTotalPages(), pageProfessor.getSize());
        List<ProfessorResponse> professores = ProfessorResponse.parse(pageProfessor.getContent());
        return new ProfessorResponseList(meta, professores);
    }

}

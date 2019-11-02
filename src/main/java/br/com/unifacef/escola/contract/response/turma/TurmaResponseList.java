package br.com.unifacef.escola.contract.response.turma;

import br.com.unifacef.escola.contract.response.general.MetaResponse;
import br.com.unifacef.escola.contract.response.materia.MateriaResponse;
import br.com.unifacef.escola.contract.response.materia.MateriaResponseList;
import br.com.unifacef.escola.model.Materia;
import br.com.unifacef.escola.model.Turma;
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
public class TurmaResponseList {

    private MetaResponse meta;
    private List<TurmaResponse> content;

    public static TurmaResponseList parse(Page<Turma> pageTurma) {
        MetaResponse meta = new MetaResponse(pageTurma.getTotalElements(), pageTurma.getTotalPages(), pageTurma.getSize());
        List<TurmaResponse> turmas = TurmaResponse.parse(pageTurma.getContent());
        return new TurmaResponseList(meta, turmas);
    }

}

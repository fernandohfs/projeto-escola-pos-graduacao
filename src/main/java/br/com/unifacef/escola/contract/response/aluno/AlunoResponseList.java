package br.com.unifacef.escola.contract.response.aluno;

import br.com.unifacef.escola.contract.response.general.MetaResponse;
import br.com.unifacef.escola.model.Aluno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoResponseList {

    private MetaResponse meta;
    private List<AlunoResponse> content;

    public static AlunoResponseList parse(Page<Aluno> pageAluno) {
        MetaResponse meta = MetaResponse.builder()
                .totalElements(pageAluno.getTotalElements())
                .totalPages(pageAluno.getTotalPages())
                .size(pageAluno.getSize())
                .build();

        List<AlunoResponse> alunos = AlunoResponse.parse(pageAluno.getContent());

        return AlunoResponseList.builder().meta(meta).content(alunos).build();
    }

}

package br.com.unifacef.escola.contract.response.curso;

import br.com.unifacef.escola.contract.response.general.MetaResponse;
import br.com.unifacef.escola.model.Curso;
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
public class CursoResponseList {

    private MetaResponse meta;
    private List<CursoResponse> content;

    public static CursoResponseList parse(Page<Curso> cursoPage) {
        MetaResponse meta = MetaResponse.builder()
                .totalElements(cursoPage.getTotalElements())
                .totalPages(cursoPage.getTotalPages())
                .size(cursoPage.getSize())
                .build();

        List<CursoResponse> cursos = CursoResponse.parse(cursoPage.getContent());

        return CursoResponseList.builder().meta(meta).content(cursos).build();
    }

}

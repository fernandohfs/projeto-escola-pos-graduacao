package br.com.unifacef.escola.contract.response.materia;

import br.com.unifacef.escola.contract.response.general.MetaResponse;
import br.com.unifacef.escola.model.Materia;
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
public class MateriaResponseList {

    private MetaResponse meta;
    private List<MateriaResponse> content;

    public static MateriaResponseList parse(Page<Materia> pageMateria) {
        MetaResponse meta = new MetaResponse(pageMateria.getTotalElements(), pageMateria.getTotalPages(), pageMateria.getSize());
        List<MateriaResponse> materias = MateriaResponse.parse(pageMateria.getContent());
        return new MateriaResponseList(meta, materias);
    }

}

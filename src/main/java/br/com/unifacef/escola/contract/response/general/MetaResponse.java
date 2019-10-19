package br.com.unifacef.escola.contract.response.general;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetaResponse {

    private Long totalElements;
    private Integer totalPages;
    private Integer size;

}

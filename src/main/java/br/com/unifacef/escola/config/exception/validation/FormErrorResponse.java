package br.com.unifacef.escola.config.exception.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FormErrorResponse {

    private String field;
    private String erro;

}

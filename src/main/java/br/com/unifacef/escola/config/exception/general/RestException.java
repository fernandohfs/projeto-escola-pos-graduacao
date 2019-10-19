package br.com.unifacef.escola.config.exception.general;

import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestException {

    private EntityNotFoundException notFoundException;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response notFound(EntityNotFoundException ex) {
        return new Response("Recurso solicitado não foi encontrado");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response notFound(HttpMessageNotReadableException ex) {
        System.out.println(ex.getMessage());
        return new Response("JSON não está no formato correto, verifique");
    }

    @ExceptionHandler(DataException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response notFound(DataException ex) {
        return new Response("Ocorreu um erro na API, reporte aos desenvolvedores da API");
    }
}

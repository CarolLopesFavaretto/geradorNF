package br.com.itau.geradornotafiscal.framework.exceptions.handler;

import br.com.itau.geradornotafiscal.framework.exceptions.RegimeTributarioInvalidoException;
import br.com.itau.geradornotafiscal.framework.exceptions.TipoPessoaInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {

    //adicionado classe de handler para melhor tratativa de erros

    @ExceptionHandler(TipoPessoaInvalidoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage tipoPessoaInvalidoException(TipoPessoaInvalidoException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(true));
    }

    @ExceptionHandler(RegimeTributarioInvalidoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage regimeTributarioInvalidoException(RegimeTributarioInvalidoException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}

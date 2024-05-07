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

    @ExceptionHandler(TipoPessoaInvalidoException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage tipoPessoaInvalidoException(TipoPessoaInvalidoException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(true));
    }

//    @ExceptionHandler(TipoPessoaInvalidoException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ResponseEntity<String> handleException(TipoPessoaInvalidoException exception,
//                                                  HttpServletRequest request) {
//        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler(RegimeTributarioInvalidoException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage regimeTributarioInvalidoException(RegimeTributarioInvalidoException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }
}

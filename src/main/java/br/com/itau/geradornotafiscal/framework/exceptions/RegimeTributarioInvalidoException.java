package br.com.itau.geradornotafiscal.framework.exceptions;

public class RegimeTributarioInvalidoException extends RuntimeException {

    public RegimeTributarioInvalidoException(String mensagem) {
        super(mensagem);
    }
}

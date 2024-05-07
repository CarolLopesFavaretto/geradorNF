package br.com.itau.geradornotafiscal.framework.exceptions;

public class TipoPessoaInvalidoException extends RuntimeException {

    public TipoPessoaInvalidoException(String mensagem) {
        super(mensagem);
    }
}

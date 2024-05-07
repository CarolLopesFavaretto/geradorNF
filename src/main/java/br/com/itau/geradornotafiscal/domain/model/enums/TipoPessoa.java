package br.com.itau.geradornotafiscal.domain.model.enums;

import br.com.itau.geradornotafiscal.framework.exceptions.TipoPessoaInvalidoException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoPessoa {
    FISICA,
    JURIDICA;

    @JsonCreator
    public static TipoPessoa fromString(String value) {
        for (TipoPessoa tipoPessoa : TipoPessoa.values()) {
            if (tipoPessoa.name().equalsIgnoreCase(value)) {
                return tipoPessoa;
            }
        }
        throw new TipoPessoaInvalidoException("Tipo de pessoa inválida: " + value);
    }
}



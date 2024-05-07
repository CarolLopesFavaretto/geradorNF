package br.com.itau.geradornotafiscal.domain.model.enums;

import br.com.itau.geradornotafiscal.framework.exceptions.RegimeTributarioInvalidoException;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum RegimeTributacaoPJ {
    SIMPLES_NACIONAL,
    LUCRO_REAL,
    LUCRO_PRESUMIDO,
    OUTROS;

    @JsonCreator
    public static RegimeTributacaoPJ fromString(String value) {
        for (RegimeTributacaoPJ tributacaoPJ : RegimeTributacaoPJ.values()) {
            if (tributacaoPJ.name().equalsIgnoreCase(value)) {
                return tributacaoPJ;
            }
        }
        throw new RegimeTributarioInvalidoException("Tipo de regime tributario inválida: " + value);
    }
}


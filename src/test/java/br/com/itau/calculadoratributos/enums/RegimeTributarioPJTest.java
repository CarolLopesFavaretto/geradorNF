package br.com.itau.calculadoratributos.enums;

import br.com.itau.geradornotafiscal.domain.model.Destinatario;
import br.com.itau.geradornotafiscal.domain.model.enums.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.domain.model.enums.TipoPessoa;
import br.com.itau.geradornotafiscal.framework.exceptions.RegimeTributarioInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegimeTributarioPJTest {

    @Test
    void regimeTributarioInvalidoException() {
        assertThrows(RegimeTributarioInvalidoException.class, () -> RegimeTributacaoPJ.fromString("N/A" ));
    }

    @Test
    void regimeTributarioValido() {
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.OUTROS);

        assertEquals(RegimeTributacaoPJ.OUTROS, destinatario.getRegimeTributacao());
    }
}

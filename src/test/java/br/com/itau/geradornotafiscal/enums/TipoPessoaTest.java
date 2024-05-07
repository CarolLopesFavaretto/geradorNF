package br.com.itau.geradornotafiscal.enums;

import br.com.itau.geradornotafiscal.domain.model.Destinatario;
import br.com.itau.geradornotafiscal.domain.model.enums.TipoPessoa;
import br.com.itau.geradornotafiscal.framework.exceptions.TipoPessoaInvalidoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TipoPessoaTest {

    @Test
    void tipoPessoaInvalidoException() {
        assertThrows(TipoPessoaInvalidoException.class, () -> TipoPessoa.fromString("Na" ));
    }

    @Test
    void tipoPessoaValida() {
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.FISICA);

        assertEquals(TipoPessoa.FISICA, destinatario.getTipoPessoa());
    }
}




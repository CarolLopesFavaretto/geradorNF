package br.com.itau.geradornotafiscal.aplication.port.out;

import br.com.itau.geradornotafiscal.domain.model.NotaFiscal;

public class FinanceiroService {
    public void enviarNotaFiscalParaContasReceber(NotaFiscal notaFiscal) {

        try {
            //Simula o envio da nota fiscal para o contas a receber
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

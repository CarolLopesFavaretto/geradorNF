package br.com.itau.geradornotafiscal.aplication.port.out;

import br.com.itau.geradornotafiscal.domain.model.NotaFiscal;

public class FinanceiroService {

    //        TODO - sugestao de implementacao de um servico assincrono;
    public void enviarNotaFiscalParaContasReceber(NotaFiscal notaFiscal) {

        try {
            //Simula o envio da nota fiscal para o contas a receber
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

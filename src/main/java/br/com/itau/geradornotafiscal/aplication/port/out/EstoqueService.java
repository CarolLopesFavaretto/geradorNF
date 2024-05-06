package br.com.itau.geradornotafiscal.aplication.port.out;


import br.com.itau.geradornotafiscal.domain.model.NotaFiscal;

public class EstoqueService {
    public void enviarNotaFiscalParaBaixaEstoque(NotaFiscal notaFiscal) {
        try {
            //Simula envio de nota fiscal para baixa de estoque
            Thread.sleep(380);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

package br.com.itau.geradornotafiscal.aplication.port.in;


import br.com.itau.geradornotafiscal.domain.model.NotaFiscal;
import br.com.itau.geradornotafiscal.domain.model.Pedido;

public interface GeradorNotaFiscalService {

    public NotaFiscal gerarNotaFiscal(Pedido pedido);

}

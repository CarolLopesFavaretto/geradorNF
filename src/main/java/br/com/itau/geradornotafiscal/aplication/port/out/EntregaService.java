package br.com.itau.geradornotafiscal.aplication.port.out;

import br.com.itau.geradornotafiscal.domain.model.NotaFiscal;
import br.com.itau.geradornotafiscal.framework.out.EntregaIntegrationPort;

public class EntregaService {
    public void agendarEntrega(NotaFiscal notaFiscal) {

        //        TODO - sugestao de implementacao de um servico assincrono;

        try {
            //Simula o agendamento da entrega
            Thread.sleep(150);
            new EntregaIntegrationPort().criarAgendamentoEntrega(notaFiscal);
        } catch (InterruptedException e) {
            throw new
                    RuntimeException(e);
        }

    }
}

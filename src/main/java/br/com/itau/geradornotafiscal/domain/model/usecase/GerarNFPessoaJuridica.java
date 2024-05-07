package br.com.itau.geradornotafiscal.domain.model.usecase;

import br.com.itau.geradornotafiscal.aplication.service.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.domain.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.domain.model.Pedido;
import br.com.itau.geradornotafiscal.domain.model.enums.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.framework.exceptions.RegimeTributarioInvalidoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GerarNFPessoaJuridica extends GeradorNF {

    Logger logger = LoggerFactory.getLogger(GerarNFPessoaJuridica.class);

    @Override
    public List<ItemNotaFiscal> calcularNF(Pedido pedido,
                                           CalculadoraAliquotaProduto calculadoraAliquotaProduto) {
        RegimeTributacaoPJ regimeTributacao = pedido.getDestinatario().getRegimeTributacao();

        logger.debug("calculo pessoa juridica de acordo com o regime tributario");
        GerarNFPessoaJuridica regime = getGeradorNFPorRegime(regimeTributacao);
        return regime.calcularNF(pedido, calculadoraAliquotaProduto);
    }

    private GerarNFPessoaJuridica getGeradorNFPorRegime(RegimeTributacaoPJ regimeTributacaoPJ) {
        switch (regimeTributacaoPJ) {
            case SIMPLES_NACIONAL:
                return new GerarNFPessoaJuridicaSimplesNacional();
            case LUCRO_REAL:
                return new GerarNFPessoaJuridicaLucroReal();
            case LUCRO_PRESUMIDO:
                return new GerarNFPessoaJuridicaLucroPresumido();
            default:
                throw new RegimeTributarioInvalidoException("Regime tributario invalido");
        }
    }
}

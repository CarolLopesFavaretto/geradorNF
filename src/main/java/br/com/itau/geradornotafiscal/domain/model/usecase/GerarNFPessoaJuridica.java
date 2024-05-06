package br.com.itau.geradornotafiscal.domain.model.usecase;

import br.com.itau.geradornotafiscal.domain.model.*;
import br.com.itau.geradornotafiscal.domain.model.enums.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.aplication.service.CalculadoraAliquotaProduto;

import java.util.List;

public class GerarNFPessoaJuridica extends GeradorNF {
    @Override
    public List<ItemNotaFiscal> calcularNF(Pedido pedido,
                                           CalculadoraAliquotaProduto calculadoraAliquotaProduto)
            throws IllegalAccessException {

        RegimeTributacaoPJ regimeTributacao = pedido.getDestinatario().getRegimeTributacao();

        GerarNFPessoaJuridica regime = getGeradorNFPorRegime(regimeTributacao);
        return regime.calcularNF(pedido, calculadoraAliquotaProduto);

    }

    private GerarNFPessoaJuridica getGeradorNFPorRegime(RegimeTributacaoPJ regimeTributacaoPJ)
            throws IllegalAccessException {
        switch (regimeTributacaoPJ) {
            case SIMPLES_NACIONAL:
                return new GerarNFPessoaJuridicaSimplesNacional();
            case LUCRO_REAL:
                return new GerarNFPessoaJuridicaLucroReal();
            case LUCRO_PRESUMIDO:
                return new GerarNFPessoaJuridicaLucroPresumido();
            default:
                throw new IllegalAccessException("Regime tributario invalido");
        }
    }
}

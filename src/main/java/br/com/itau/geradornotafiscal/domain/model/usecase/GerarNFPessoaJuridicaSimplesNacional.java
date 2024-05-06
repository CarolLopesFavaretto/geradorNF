package br.com.itau.geradornotafiscal.domain.model.usecase;

import br.com.itau.geradornotafiscal.aplication.service.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.domain.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.domain.model.Pedido;

import java.util.List;

public class GerarNFPessoaJuridicaSimplesNacional extends GerarNFPessoaJuridica {

    @Override
    public List<ItemNotaFiscal> calcularNF(Pedido pedido,
                                           CalculadoraAliquotaProduto calculadoraAliquotaProduto) {

        double valorTotalItens = pedido.getValorTotalItens();
        double aliquota;

        if (valorTotalItens < 1000) {
            aliquota = 0.03;
        } else if (valorTotalItens <= 2000) {
            aliquota = 0.07;
        } else if (valorTotalItens <= 5000) {
            aliquota = 0.13;
        } else {
            aliquota = 0.19;
        }
        return calculadoraAliquotaProduto.calcularAliquota(pedido.getItens(), aliquota);
    }
}

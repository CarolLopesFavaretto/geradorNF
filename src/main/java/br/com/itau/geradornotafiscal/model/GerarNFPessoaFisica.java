package br.com.itau.geradornotafiscal.model;

import br.com.itau.geradornotafiscal.service.CalculadoraAliquotaProduto;

import java.util.List;

public class GerarNFPessoaFisica extends GeradorNF {

    @Override
    public List<ItemNotaFiscal> calcularNF(Pedido pedido,
                                           CalculadoraAliquotaProduto calculadoraAliquotaProduto) {

        double valorTotalItens = pedido.getValorTotalItens();
        double aliquota;

        if (valorTotalItens < 500) {
            aliquota = 0;
        } else if (valorTotalItens <= 2000) {
            aliquota = 0.12;
        } else if (valorTotalItens <= 3500) {
            aliquota = 0.15;
        } else {
            aliquota = 0.17;
        }

        return calculadoraAliquotaProduto.calcularAliquota(pedido.getItens(), aliquota);
    }
}
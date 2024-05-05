package br.com.itau.geradornotafiscal.model;

import br.com.itau.geradornotafiscal.service.CalculadoraAliquotaProduto;

import java.util.List;

public abstract class GeradorNF {

    public abstract List<ItemNotaFiscal> calcularNF(Pedido pedido,
                                                    CalculadoraAliquotaProduto calculadoraAliquotaProduto)
            throws IllegalAccessException;
}

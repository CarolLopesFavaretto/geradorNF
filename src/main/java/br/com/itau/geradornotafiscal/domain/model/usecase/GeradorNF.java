package br.com.itau.geradornotafiscal.domain.model.usecase;

import br.com.itau.geradornotafiscal.aplication.service.CalculadoraAliquotaProduto;
import br.com.itau.geradornotafiscal.domain.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.domain.model.Pedido;

import java.util.List;

public abstract class GeradorNF {

    public abstract List<ItemNotaFiscal> calcularNF(Pedido pedido,
                                                    CalculadoraAliquotaProduto calculadoraAliquotaProduto);
}

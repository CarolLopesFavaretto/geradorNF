package br.com.itau.geradornotafiscal.aplication.service;

import br.com.itau.geradornotafiscal.domain.model.Item;
import br.com.itau.geradornotafiscal.domain.model.ItemNotaFiscal;

import java.util.ArrayList;
import java.util.List;

public class CalculadoraAliquotaProduto {

    public List<ItemNotaFiscal> calcularAliquota(List<Item> items, double aliquotaPercentual) {

        //alterado para ser uma variavel nivel de metodo, para instanciar a variavel toda execucao do metodo
        List<ItemNotaFiscal> itemNotaFiscalList = new ArrayList<>();

        for (Item item : items) {
            double valorTributo = item.getValorUnitario() * aliquotaPercentual;
            ItemNotaFiscal itemNotaFiscal = ItemNotaFiscal.builder()
                    .idItem(item.getIdItem())
                    .descricao(item.getDescricao())
                    .valorUnitario(item.getValorUnitario())
                    .quantidade(item.getQuantidade())
                    .valorTributoItem(valorTributo)
                    .build();
            itemNotaFiscalList.add(itemNotaFiscal);
        }
        return itemNotaFiscalList;
    }
}




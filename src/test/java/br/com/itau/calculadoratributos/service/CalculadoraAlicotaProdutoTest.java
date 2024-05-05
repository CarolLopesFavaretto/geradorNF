package br.com.itau.calculadoratributos.service;

import br.com.itau.geradornotafiscal.model.Item;
import br.com.itau.geradornotafiscal.model.ItemNotaFiscal;
import br.com.itau.geradornotafiscal.service.CalculadoraAliquotaProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculadoraAlicotaProdutoTest {

    private CalculadoraAliquotaProduto calculadoraAliquotaProduto;

    @BeforeEach
    public void setup() {
        calculadoraAliquotaProduto = new CalculadoraAliquotaProduto();
    }

    @Test
    void deveCalcularAliquotaDoProduto() {

        // Criação dos mocks de itens
        Item item1 = new Item();
        item1.setIdItem("1");
        item1.setDescricao("Produto 1");
        item1.setValorUnitario(100.0);
        item1.setQuantidade(1);

        Item item2 = new Item();
        item2.setIdItem("2");
        item2.setDescricao("Produto 2");
        item2.setValorUnitario(200.0);
        item2.setQuantidade(2);

        List<Item> items = Arrays.asList(item1, item2);

        // Execução do calculo
        double aliquotaPercentual = 0.10; // 10%
        List<ItemNotaFiscal> result = calculadoraAliquotaProduto.calcularAliquota(items, aliquotaPercentual);

        // Verificacao
        assertNotNull(result);
        assertEquals(2, result.size());

        // Verifica o cálculo do valor tributo para o primeiro item
        ItemNotaFiscal resultItem1 = result.get(0);
        assertEquals(10.0, resultItem1.getValorTributoItem()); // 100 * 0.10
        assertEquals(100.0, resultItem1.getValorUnitario());
        assertEquals("Produto 1", resultItem1.getDescricao());

        // Verifica o cálculo do valor tributo para o segundo item
        ItemNotaFiscal resultItem2 = result.get(1);
        assertEquals(20.0, resultItem2.getValorTributoItem()); // 200 * 0.10
        assertEquals(200.0, resultItem2.getValorUnitario());
        assertEquals("Produto 2", resultItem2.getDescricao());
    }
}

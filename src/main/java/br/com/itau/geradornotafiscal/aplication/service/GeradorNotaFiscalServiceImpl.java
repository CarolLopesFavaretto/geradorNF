package br.com.itau.geradornotafiscal.aplication.service;

import br.com.itau.geradornotafiscal.domain.model.*;
import br.com.itau.geradornotafiscal.domain.model.enums.Finalidade;
import br.com.itau.geradornotafiscal.domain.model.enums.Regiao;
import br.com.itau.geradornotafiscal.domain.model.enums.TipoPessoa;
import br.com.itau.geradornotafiscal.domain.model.usecase.GeradorNF;
import br.com.itau.geradornotafiscal.domain.model.usecase.GerarNFPessoaFisica;
import br.com.itau.geradornotafiscal.domain.model.usecase.GerarNFPessoaJuridica;
import br.com.itau.geradornotafiscal.aplication.port.in.GeradorNotaFiscalService;
import br.com.itau.geradornotafiscal.aplication.port.out.EntregaService;
import br.com.itau.geradornotafiscal.aplication.port.out.EstoqueService;
import br.com.itau.geradornotafiscal.aplication.port.out.FinanceiroService;
import br.com.itau.geradornotafiscal.aplication.port.out.RegistroService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class GeradorNotaFiscalServiceImpl implements GeradorNotaFiscalService {
    @Override
    public NotaFiscal gerarNotaFiscal(Pedido pedido) throws IllegalAccessException {

        Destinatario destinatario = pedido.getDestinatario();
        TipoPessoa tipoPessoa = destinatario.getTipoPessoa();
        List<ItemNotaFiscal> itemNotaFiscalList;

        CalculadoraAliquotaProduto calculadoraAliquotaProduto = new CalculadoraAliquotaProduto();
        GeradorNF geradorNf = getGeradorNFPorTipoPessoa(tipoPessoa);

        itemNotaFiscalList = geradorNf.calcularNF(pedido, calculadoraAliquotaProduto);

        //Regras para frete
        Regiao regiao = destinatario.getEnderecos().stream()
                .filter(endereco -> endereco.getFinalidade() == Finalidade.ENTREGA || endereco.getFinalidade() == Finalidade.COBRANCA_ENTREGA)
                .map(Endereco::getRegiao)
                .findFirst()
                .orElse(null);

        double valorFrete = pedido.getValorFrete();
        double valorFreteComPercentual = 0;

        //sera que realmente devemos proceguir com a emissao da nf, sendo que nao temos um endereco valido?

        if (Objects.nonNull(regiao)) {
            valorFreteComPercentual = regiao.getValorFreteComPercentual(valorFrete);
        }

        // Create the NotaFiscal object
        String idNotaFiscal = UUID.randomUUID().toString();
        NotaFiscal notaFiscal = NotaFiscal.builder()
                .idNotaFiscal(idNotaFiscal)
                .data(LocalDateTime.now())
                .valorTotalItens(pedido.getValorTotalItens())
                .valorFrete(valorFreteComPercentual)
                .itens(itemNotaFiscalList)
                .destinatario(pedido.getDestinatario())
                .build();

        new EstoqueService().enviarNotaFiscalParaBaixaEstoque(notaFiscal);
        new RegistroService().registrarNotaFiscal(notaFiscal);
        new EntregaService().agendarEntrega(notaFiscal);
        new FinanceiroService().enviarNotaFiscalParaContasReceber(notaFiscal);

        return notaFiscal;
    }

    private GeradorNF getGeradorNFPorTipoPessoa(TipoPessoa tipoPessoa) throws IllegalAccessException {
        switch (tipoPessoa) {
            case FISICA:
                return new GerarNFPessoaFisica();
            case JURIDICA:
                return new GerarNFPessoaJuridica();
            default:
                throw new IllegalAccessException("Tipo invalido");
        }
    }
}
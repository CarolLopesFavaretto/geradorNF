package br.com.itau.calculadoratributos.service;

import br.com.itau.geradornotafiscal.aplication.service.GeradorNotaFiscalServiceImpl;
import br.com.itau.geradornotafiscal.domain.model.*;
import br.com.itau.geradornotafiscal.domain.model.enums.Finalidade;
import br.com.itau.geradornotafiscal.domain.model.enums.Regiao;
import br.com.itau.geradornotafiscal.domain.model.enums.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.domain.model.enums.TipoPessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeradorNotaFiscalServiceImplTest {

    @InjectMocks
    private GeradorNotaFiscalServiceImpl geradorNotaFiscalService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaFisicaComValorTotalItensMenorQue500() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(400);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.FISICA);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.NORTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(100);
        item.setQuantidade(4);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0, notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.08 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaFisicaComValorTotalItensIgualQue2000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(2000);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.FISICA);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.NORTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(2);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.12 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.08 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaFisicaComValorTotalItensMaiorQue3500() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(4000);
        pedido.setValorFrete(150);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.FISICA);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.COBRANCA_ENTREGA);
        endereco.setRegiao(Regiao.CENTRO_OESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(4);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.17 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.07 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaFisicaComFinalidadeCobrancaEFreteZerado() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(4000);
        pedido.setValorFrete(150);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.FISICA);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.COBRANCA);
        endereco.setRegiao(Regiao.CENTRO_OESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(4);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.17 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(0, notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaFisicaComValorTotalItensIgualQue3500() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(3500);
        pedido.setValorFrete(150);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.FISICA);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.COBRANCA_ENTREGA);
        endereco.setRegiao(Regiao.CENTRO_OESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1750);
        item.setQuantidade(2);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.15 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.07 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroPresumidoEValorTotalItensMaiorQue5000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(6000);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.SUDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(6);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.20 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.048 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroPresumidoEValorTotalItensMenorQue1000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(900);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.SUDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(900);
        item.setQuantidade(1);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.03 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.048 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroPresumidoEValorTotalItensMenorQue2000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(1900);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.SUL);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1900);
        item.setQuantidade(1);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.09 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.06 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroPresumidoEValorTotalItensMenorQue5000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(4900);
        pedido.setValorFrete(100);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_PRESUMIDO);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.SUL);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(4900);
        item.setQuantidade(1);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.16 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.06 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoSimplesNacionalEValorTotalItensMaiorQue5000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(8000);
        pedido.setValorFrete(400);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.SIMPLES_NACIONAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.NORDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(8);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.19 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.085 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoSimplesNacionalEValorTotalItensMenorQue2000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(1900);
        pedido.setValorFrete(400);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.SIMPLES_NACIONAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.NORTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(950);
        item.setQuantidade(2);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.07 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.08 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoSimplesNacionalEValorTotalItensMenorQue1000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(900);
        pedido.setValorFrete(400);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.SIMPLES_NACIONAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.NORTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(200);
        item.setQuantidade(2);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.03 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.08 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoSimplesNacionalEValorTotalItensMenorQue5000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(4000);
        pedido.setValorFrete(400);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.SIMPLES_NACIONAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.ENTREGA);
        endereco.setRegiao(Regiao.NORTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(4);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.13 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.08 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroRealEValorTotalItensMaiorQue5000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(9000);
        pedido.setValorFrete(300);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_REAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.COBRANCA_ENTREGA);
        endereco.setRegiao(Regiao.NORDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1000);
        item.setQuantidade(9);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.20 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.085 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroRealEValorTotalItensMenorQue5000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(4500);
        pedido.setValorFrete(300);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_REAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.COBRANCA_ENTREGA);
        endereco.setRegiao(Regiao.NORDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1500);
        item.setQuantidade(3);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.15 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.085 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroRealEValorTotalItensMenorQue1000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(900);
        pedido.setValorFrete(300);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_REAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.COBRANCA_ENTREGA);
        endereco.setRegiao(Regiao.NORDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(900);
        item.setQuantidade(1);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.03 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.085 * pedido.getValorFrete(), notaFiscal.getValorFrete());

    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroRealEValorTotalItensMenorQue2000() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(1900);
        pedido.setValorFrete(300);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_REAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.COBRANCA_ENTREGA);
        endereco.setRegiao(Regiao.NORDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1900);
        item.setQuantidade(1);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.09 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(1.085 * pedido.getValorFrete(), notaFiscal.getValorFrete());
    }

    @Test
    void deveGerarNotaFiscalParaTipoPessoaJuridicaComRegimeTributacaoLucroRealComFinalidadeOutrosEFreteZerado() {
        Pedido pedido = new Pedido();
        pedido.setValorTotalItens(1900);
        pedido.setValorFrete(300);
        Destinatario destinatario = new Destinatario();
        destinatario.setTipoPessoa(TipoPessoa.JURIDICA);
        destinatario.setRegimeTributacao(RegimeTributacaoPJ.LUCRO_REAL);

        // Create and add Endereco to the Destinatario
        Endereco endereco = new Endereco();
        endereco.setFinalidade(Finalidade.OUTROS);
        endereco.setRegiao(Regiao.NORDESTE);
        destinatario.setEnderecos(Arrays.asList(endereco));

        pedido.setDestinatario(destinatario);

        // Create and add items to the Pedido
        Item item = new Item();
        item.setValorUnitario(1900);
        item.setQuantidade(1);
        pedido.setItens(Arrays.asList(item));

        NotaFiscal notaFiscal = geradorNotaFiscalService.gerarNotaFiscal(pedido);

        assertEquals(pedido.getValorTotalItens(), notaFiscal.getValorTotalItens());
        assertEquals(1, notaFiscal.getItens().size());
        assertEquals(0.09 * item.getValorUnitario(), notaFiscal.getItens().get(0).getValorTributoItem());
        assertEquals(0, notaFiscal.getValorFrete());
    }

}
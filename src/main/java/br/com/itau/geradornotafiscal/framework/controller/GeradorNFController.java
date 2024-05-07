package br.com.itau.geradornotafiscal.framework.controller;


import br.com.itau.geradornotafiscal.aplication.port.in.GeradorNotaFiscalService;
import br.com.itau.geradornotafiscal.domain.model.NotaFiscal;
import br.com.itau.geradornotafiscal.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedido" )
public class GeradorNFController {

    @Autowired
    private GeradorNotaFiscalService notaFiscalService;

    @PostMapping("/gerarNotaFiscal" )
    public ResponseEntity<NotaFiscal> gerarNotaFiscal(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(notaFiscalService.gerarNotaFiscal(pedido));
    }
}

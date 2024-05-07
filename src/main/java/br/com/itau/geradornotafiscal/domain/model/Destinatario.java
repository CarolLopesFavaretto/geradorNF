package br.com.itau.geradornotafiscal.domain.model;

import br.com.itau.geradornotafiscal.domain.model.enums.RegimeTributacaoPJ;
import br.com.itau.geradornotafiscal.domain.model.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Destinatario {
    @JsonProperty("nome")
    private String nome;

    @JsonProperty("tipo_pessoa")
    private TipoPessoa tipoPessoa;

    @JsonProperty("regime_tributacao")
    private RegimeTributacaoPJ regimeTributacao;

    @JsonProperty("documentos")
    private List<Documento> documentos;

    @JsonProperty("enderecos")
    private List<Endereco> enderecos;

}





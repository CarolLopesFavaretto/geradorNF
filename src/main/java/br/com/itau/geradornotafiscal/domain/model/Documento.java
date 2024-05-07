package br.com.itau.geradornotafiscal.domain.model;

import br.com.itau.geradornotafiscal.domain.model.enums.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Documento {

    @JsonProperty("numero")
    private String numero;
    @JsonProperty("tipo")
    private TipoDocumento tipo;

}

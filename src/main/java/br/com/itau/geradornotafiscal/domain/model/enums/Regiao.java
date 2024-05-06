package br.com.itau.geradornotafiscal.domain.model.enums;


public enum Regiao {
    NORTE(1.08),
    NORDESTE(1.085),
    CENTRO_OESTE(1.07),
    SUDESTE(1.048),
    SUL(1.06);

    Regiao(double baseCalculo) {
        this.baseCalculo = baseCalculo;
    }
    private double baseCalculo;

    public double getValorFreteComPercentual(double valorFrete){
        return this.baseCalculo * valorFrete;
    }
}

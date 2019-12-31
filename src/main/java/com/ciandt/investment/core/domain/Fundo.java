package com.ciandt.investment.core.domain;

import java.math.BigDecimal;

public class Fundo {

    private BigDecimal captacaoLiquida;
    private String cnpj;

    public Fundo(String cnpj, BigDecimal captacaoLiquida) {
        this.cnpj = cnpj;
        this.captacaoLiquida = captacaoLiquida;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BigDecimal getCaptacaoLiquida() {
        return this.captacaoLiquida;
    }

    public void setCaptacaoLiquida(BigDecimal captacaoLiquida) {
        this.captacaoLiquida = captacaoLiquida;
    }

    @Override
    public String toString() {
        return "CNPJ: " + this.captacaoLiquida + " Captacao Liquida: " + this.captacaoLiquida;
    }
}

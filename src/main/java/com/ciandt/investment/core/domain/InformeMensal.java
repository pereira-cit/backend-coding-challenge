package com.ciandt.investment.core.domain;

import java.math.BigDecimal;

public class InformeMensal {
    private String cnpj;
    private String mesReferencia;
    private BigDecimal valorTotal;
    private BigDecimal valorCota;
    private BigDecimal valorPatrimonioLiquido;
    private BigDecimal captacaoDia;
    private BigDecimal resgateDia;
    private Integer numeroCotistas;

    public InformeMensal(String cnpj, String mesReferencia, BigDecimal valorTotal, BigDecimal valorCota, BigDecimal valorPatrimonioLiquido, BigDecimal captacaoDia, BigDecimal resgateDia, Integer numeroCotistas) {
        setCnpj(cnpj);
        setMesReferencia(mesReferencia);
        setValorTotal(valorTotal);
        setValorCota(valorCota);
        setValorPatrimonioLiquido(valorPatrimonioLiquido);
        setCaptacaoDia(captacaoDia);
        setResgateDia(resgateDia);
        setNumeroCotistas(numeroCotistas);
    }

    public InformeMensal(String cnpj, BigDecimal captacaoDia, BigDecimal resgateDia) {
        setCnpj(cnpj);
        setCaptacaoDia(captacaoDia);
        setResgateDia(resgateDia);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(String mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorCota() {
        return valorCota;
    }

    public void setValorCota(BigDecimal valorCota) {
        this.valorCota = valorCota;
    }

    public BigDecimal getValorPatrimonioLiquido() {
        return valorPatrimonioLiquido;
    }

    public void setValorPatrimonioLiquido(BigDecimal valorPatrimonioLiquido) {
        this.valorPatrimonioLiquido = valorPatrimonioLiquido;
    }

    public BigDecimal getCaptacaoDia() {
        return captacaoDia;
    }

    public void setCaptacaoDia(BigDecimal captacaoDia) {
        this.captacaoDia = captacaoDia;
    }

    public BigDecimal getResgateDia() {
        return resgateDia;
    }

    public void setResgateDia(BigDecimal resgateDia) {
        this.resgateDia = resgateDia;
    }

    public Integer getNumeroCotistas() {
        return numeroCotistas;
    }

    public void setNumeroCotistas(Integer numeroCotistas) {
        this.numeroCotistas = numeroCotistas;
    }

}

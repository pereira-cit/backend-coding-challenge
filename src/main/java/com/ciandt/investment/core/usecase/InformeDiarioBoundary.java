package com.ciandt.investment.core.usecase;

import com.ciandt.investment.core.domain.Fundo;
import com.ciandt.investment.core.domain.InformeDiario;

import java.math.BigDecimal;
import java.util.List;

public interface InformeDiarioBoundary {

    List<InformeDiario> getAll();

    BigDecimal getCapitacaoLiquidaByCnpj(String cnpj);

    List<Fundo> getTopTen();
}

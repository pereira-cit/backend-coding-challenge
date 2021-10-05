package com.ciandt.investment.core.domain;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class GeraRelatorioMensal {

    // retorna um Map(CNPJ,CAPTACOES) - da lista de informes diarios
    private Map<String, BigDecimal> captacoesMes(List<InformeDiario> informeDiarios) {
        Map<String, BigDecimal> setCaptacoesMes = new HashMap<>();
        for (InformeDiario informeDiario : informeDiarios) {
            setCaptacoesMes.put(informeDiario.getCnpj(), informeDiario.getCaptacaoDia());
        }

        return setCaptacoesMes;
    }

    private Map<String, BigDecimal> resgatesMes(List<InformeDiario> informeDiarios) {
        Map<String, BigDecimal> setCaptacoesMes = new HashMap<>();
        for (InformeDiario informeDiario : informeDiarios) {
            setCaptacoesMes.put(informeDiario.getCnpj(), informeDiario.getResgateDia());
        }

        return setCaptacoesMes;
    }

    // Retorna um Map(CNPJ,CAPTAÇÃOLIQ) - dirença entre Captação e Resgate
    // Passar lista de iformes e quantas empresas filtrar - retorno em ordem decrescente
    public Map<String, BigDecimal> topFundosCaptacaoLiquida(List<InformeDiario> informeDiarios, int top) {
        Map<String, BigDecimal> setTopFundosCaptacaoLiquida = new HashMap<>();
        Map<String, BigDecimal> captacoesMes = captacoesMes(informeDiarios);
        Map<String, BigDecimal> resgatesMes = resgatesMes(informeDiarios);

        for (String cnpj : captacoesMes.keySet()) {
            if (resgatesMes.containsKey(cnpj)) {
                BigDecimal result =  captacoesMes.get(cnpj).subtract(resgatesMes.get(cnpj));
                setTopFundosCaptacaoLiquida.put(cnpj, result);
            }
        }

        return setTopFundosCaptacaoLiquida.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(top)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (s1, s2) -> s1, LinkedHashMap::new));
    }

// https://stackoverflow.com/questions/5792235/efficient-way-to-subtract-values-in-two-hashmaps-by-key
// https://stackoverflow.com/questions/62803862/how-to-sort-treemap-values-in-descending-order-and-how-to-limit-the-output

}

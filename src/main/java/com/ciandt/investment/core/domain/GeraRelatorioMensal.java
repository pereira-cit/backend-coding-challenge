package com.ciandt.investment.core.domain;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class GeraRelatorioMensal {

    private Map<String, BigDecimal> captacoesMes(List<InformeDiario> informeDiarios) {
        Map<String, BigDecimal> setCaptacoesMes = new HashMap<>();
        for (InformeDiario informeDiario : informeDiarios) {
            setCaptacoesMes.put(informeDiario.getCnpj(), informeDiario.getCaptacaoDia());
        }

        Map<String, BigDecimal> filterSetCaptacoesMes = new TreeMap<>(Collections.reverseOrder());
        for (String cnpj : setCaptacoesMes.keySet()) {
            BigDecimal value = setCaptacoesMes.get(cnpj);
            // System.out.println(cnpj + "=" + value);
            filterSetCaptacoesMes.put(cnpj, value);
        }
        return filterSetCaptacoesMes;
    }

    private Map<String, BigDecimal> resgatesMes(List<InformeDiario> informeDiarios) {
        Map<String, BigDecimal> setCaptacoesMes = new HashMap<>();
        for (InformeDiario informeDiario : informeDiarios) {
            setCaptacoesMes.put(informeDiario.getCnpj(), informeDiario.getResgateDia());
        }

        Map<String, BigDecimal> filterSetResgatesMes = new TreeMap<>(Collections.reverseOrder());
        for (String cnpj : setCaptacoesMes.keySet()) {
            BigDecimal value = setCaptacoesMes.get(cnpj);
            filterSetResgatesMes.put(cnpj, value);
        }
        return filterSetResgatesMes;
    }

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

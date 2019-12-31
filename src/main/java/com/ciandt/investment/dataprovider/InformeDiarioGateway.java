package com.ciandt.investment.dataprovider;

import com.ciandt.investment.core.domain.Fundo;
import com.ciandt.investment.core.domain.InformeDiario;
import com.ciandt.investment.core.usecase.InformeDiarioBoundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class InformeDiarioGateway implements InformeDiarioBoundary {

    private static final int HEADER_LINE = 1;

    /**
     * Método para obter todas as captações a partir do arquivo .csv localizado no diretório "informes"
     * @return
     */
    @Override
    public List<InformeDiario> getAll() {

        try {

            final Path path = Paths.get("src/main/resources/informes", "inf_diario_fi_201907.csv");
            Reader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));

            try (BufferedReader dataset = new BufferedReader(reader)) {
                return dataset
                        .lines()
                        .skip(HEADER_LINE)
                        .map(InformeDiario::new)
                        .collect(Collectors.toList());
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("Arquivo não encontrado");
        }
    }

    /**
     * Método para "testar" se o algoritmo para agrupar a captação está correto.
     * @param cnpj
     * @return A captação liquida do mes
     */
    @Override
    public BigDecimal getCapitacaoLiquidaByCnpj(String cnpj) {

        System.out.println("Calculando a Capitação Liquida do CNPJ => " + cnpj);

        List<BigDecimal> capitacaoList = new ArrayList();

        List<InformeDiario> all = getAll();

        all.forEach(informeDiario -> {
            if (informeDiario.getCnpj().equals(cnpj)) {
                capitacaoList.add(informeDiario.getCaptacaoLiquida());
            }
        });

        return capitacaoList
                .stream()
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    /**
     * Método para obter os 10 melhores fundos baseado na captação líquida do mês.
     * @return
     */
    @Override
    public List<Fundo> getTopTen() {

        Map<String, BigDecimal> mapaDeCaptacoes = getMapaDeCaptacoes();

        List<Fundo> fundos = new ArrayList<>();

        mapaDeCaptacoes.entrySet().stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .limit(10)
                .forEach(entry -> {
                    Fundo fundo = new Fundo(entry.getKey(), entry.getValue());
                    fundos.add(fundo);
                });

        return fundos;
    }

    private Map<String, BigDecimal> getMapaDeCaptacoes() {

        List<InformeDiario> all = getAll();

        Map<String, BigDecimal> mapaDeCaptacoes = new HashMap<>();

        all.forEach(informeDiario -> {

            String cnpj = informeDiario.getCnpj();

            if (mapaDeCaptacoes.containsKey(cnpj)) {
                mapaDeCaptacoes.replace(cnpj, mapaDeCaptacoes.get(cnpj).add(informeDiario.getCaptacaoLiquida()));
            } else {
                mapaDeCaptacoes.put(cnpj, informeDiario.getCaptacaoLiquida());
            }
        });

        return mapaDeCaptacoes;
    }

}

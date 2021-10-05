package com.ciandt.investment.dataprovider;

import com.ciandt.investment.core.domain.InformeDiario;
import com.ciandt.investment.core.usecase.InformeDiarioBoundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InformeDiarioGateway implements InformeDiarioBoundary {

    private static final int HEADER_LINE = 1;

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
}

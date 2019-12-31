package com.ciandt.investment;

import com.ciandt.investment.core.domain.Fundo;
import com.ciandt.investment.core.domain.InformeDiario;
import com.ciandt.investment.dataprovider.InformeDiarioGateway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.*;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

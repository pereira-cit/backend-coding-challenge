import com.ciandt.investment.core.domain.GeraRelatorioMensal;
import com.ciandt.investment.core.domain.InformeDiario;
import com.ciandt.investment.dataprovider.InformeDiarioGateway;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class mainTest {
    public static void main(String[] args) {

        InformeDiarioGateway informeDiarioGateway = new InformeDiarioGateway();
        List<InformeDiario> informeDiarios = informeDiarioGateway.getAll();

        GeraRelatorioMensal geraRelatorioMensal = new GeraRelatorioMensal();
        Map<String, BigDecimal> top10FundosCaptacaoLiquida = geraRelatorioMensal.topFundosCaptacaoLiquida(informeDiarios, 10);

        int count = 0;
        for (String key : top10FundosCaptacaoLiquida.keySet()) {
            count++;
            System.out.println(count + " - " + key + ": " + top10FundosCaptacaoLiquida.get(key));
        }
    }
}

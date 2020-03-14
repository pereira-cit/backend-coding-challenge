package com.ciandt.investment.com.ciandt.investment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciandt.investment.core.domain.InformeDiario;
import com.ciandt.investment.dataprovider.InformeDiarioGateway;

import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/top10funds2019July")
public class Service {

	/**
	 * Serviço que disponibiliza os 10 fundos com maior captação liquida no mês de
	 * Julho.
	 * 
	 * @author Priscilla Izabelle Dias Caldeira - 11/03/2020
	 *
	 */

	@GetMapping("/")
	public String getTop10Funds2019July() {

		InformeDiarioGateway gateway = new InformeDiarioGateway();

		List<InformeDiario> listGateway = gateway.getAll();
        HashMap<String, BigDecimal> listTopTen = new HashMap<String, BigDecimal>();
        StringBuffer sbf = new StringBuffer("");
	
        listTopTen = getPositiveNetFunding(listGateway, listTopTen);
        System.out.println(listTopTen.size());
		List<Entry<String, BigDecimal>> list = getSortList(listTopTen);
        System.out.println(list.size());
	    List<Entry<String, BigDecimal>> first10ElementsList = list.stream().limit(10).collect(Collectors.toList());	       
	          
	    for (Entry<String, BigDecimal> element : first10ElementsList) {
	        	sbf.append("Fundo : " +element.getKey() + " - Captação Líquida: " + element.getValue() + "\n" );
	    }
	        
		return sbf.toString();

	}

	public List<Entry<String, BigDecimal>> getSortList(HashMap<String, BigDecimal> listTopTen) {
		Set<Entry<String, BigDecimal>> set = listTopTen.entrySet();
	     List<Entry<String, BigDecimal>> list = new ArrayList<Entry<String, BigDecimal>>(
	                set);
	        Collections.sort(list, new Comparator<Map.Entry<String, BigDecimal>>() {
	            public int compare(Map.Entry<String, BigDecimal> o1,
	                    Map.Entry<String, BigDecimal> o2) {
	                return o2.getValue().compareTo(o1.getValue());
	            }
	        });
		return list;
	}

	public HashMap<String, BigDecimal> getPositiveNetFunding(List<InformeDiario> listGateway,HashMap<String, BigDecimal> listTopTen ) {
		for (InformeDiario informeDiario : listGateway) {
			if( informeDiario.getCaptacaoDia().subtract(informeDiario.getResgateDia()).compareTo(BigDecimal.ZERO) >0) {
				listTopTen.put(informeDiario.getCnpj(), informeDiario.getCaptacaoDia().subtract(informeDiario.getResgateDia()));
			}
		}
		return listTopTen;
	}
}

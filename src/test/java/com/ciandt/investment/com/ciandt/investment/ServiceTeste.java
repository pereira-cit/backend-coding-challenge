package com.ciandt.investment.com.ciandt.investment;
import com.ciandt.investment.com.ciandt.investment.Service;
import com.ciandt.investment.core.domain.InformeDiario;
import com.ciandt.investment.dataprovider.InformeDiarioGateway;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ServiceTeste {
	Service serviceTest = new Service();
	InformeDiarioGateway gateway = new InformeDiarioGateway();

	

    @Test
    public void getPositiveNetFundingTest() {

		List<InformeDiario> listGateway = gateway.getAll();
        HashMap<String, BigDecimal> listTopTen = new HashMap<String, BigDecimal>();
        listTopTen = serviceTest.getPositiveNetFunding(listGateway, listTopTen);
        Assert.assertEquals(6612, listTopTen.size());
    }
    
    
    @Test
    public void getSortListTest() {

    	List<InformeDiario> listGateway = gateway.getAll();
        HashMap<String, BigDecimal> listTopTen = new HashMap<String, BigDecimal>();
        listTopTen = serviceTest.getPositiveNetFunding(listGateway, listTopTen);
		List<Entry<String, BigDecimal>> list = serviceTest.getSortList(listTopTen);
        Assert.assertEquals(6612, list.size());
    }
    
    
    @Test
    public void getFirst10ElementsListTest() {


    	List<InformeDiario> listGateway = gateway.getAll();
        HashMap<String, BigDecimal> listTopTen = new HashMap<String, BigDecimal>();
        listTopTen = serviceTest.getPositiveNetFunding(listGateway, listTopTen);    	
    	List<Entry<String, BigDecimal>> list = serviceTest.getSortList(listTopTen);
        List<Entry<String, BigDecimal>> first10ElementsList = list.stream().limit(10).collect(Collectors.toList());	       
          
        Assert.assertEquals(10, first10ElementsList.size());
    }
  
}

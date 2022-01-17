package currencies.services.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import currencies.model.Currency;
import currencies.repositories.CurrencyRepo;
import currencies.services.CurrencyService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	
	private String apiString = "https://freecurrencyapi.net/api/v2/latest?apikey=";
	
	// HTTP headers
	final String apiKey = System.getenv("APP_KEY");

	@Override
	public CommandLineRunner getCurrencyAPI(CurrencyRepo repo) {
		
		Map<String, Double> repoMap = new HashMap();
		
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(apiString + apiKey);
		
		String strResponse = "";
		
		try {
			HttpResponse response = httpClient.execute(getRequest);
			HttpEntity entity = response.getEntity();
			strResponse = EntityUtils.toString(entity);
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		repoMap = getCurrencyMap(strResponse);
		
		repoMap.entrySet().forEach(entry -> {
			repo.save(new Currency(entry.getKey(), entry.getValue()));
		});
		
		return null;
	}

	@Override
	public Map<String, Double> getCurrencyMap(String jsonString) {
		
		final Map<String, Double> endProduct = new HashMap();
		
	    String first = jsonString.substring(112, jsonString.length()-2);
	    
	    String[] split = first.split(",");
	    
	    for(String str : split) {
	    	
	    	String currency = str.substring(1, 4);
	    	double value = Double.parseDouble(str.substring(6, str.length()));
	    	
	    	endProduct.put(currency, value);
	    }
		
		return endProduct;
	}

}

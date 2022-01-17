package currencies.services;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;

import currencies.repositories.CurrencyRepo;

public interface CurrencyService {
	
	CommandLineRunner getCurrencyAPI(CurrencyRepo repo);
	Map<String, Double> getCurrencyMap(String jsonString);

}

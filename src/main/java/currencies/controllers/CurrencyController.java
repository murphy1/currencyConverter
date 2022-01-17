package currencies.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import currencies.model.Currency;
import currencies.repositories.CurrencyRepo;
import currencies.services.CurrencyService;

@RestController
public class CurrencyController {
	
	private CurrencyRepo repo;
	private CurrencyService currencyService;
	
	public CurrencyController(CurrencyRepo repo, CurrencyService currencyService){
		this.repo = repo;
		this.currencyService = currencyService;
		currencyService.getCurrencyAPI(repo);
	}
	
	@GetMapping("/currencies")
	List<Currency> all(){
		return repo.findAll();
	}
	
	@GetMapping("/currencies/{name}")
	Optional<Currency> getCurrencyByName(@PathVariable String name) {
		return repo.findById(name.toUpperCase());
	}

}

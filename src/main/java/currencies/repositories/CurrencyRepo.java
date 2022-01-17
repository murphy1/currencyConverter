package currencies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import currencies.model.Currency;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency, String> {

}

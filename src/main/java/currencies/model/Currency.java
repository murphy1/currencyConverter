package currencies.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Currency {
	
	private @Id String name;
	private Double value;
	
	public Currency(){}
	
	public Currency(String name, Double value){
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}

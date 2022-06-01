package pe.com.bootcamp.microservice.account.deposit.service;

@FunctionalInterface
public interface CalculationService {
	Double Calcule(Double amount, Double balance);
}

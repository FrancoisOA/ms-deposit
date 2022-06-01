package pe.com.bootcamp.microservice.account.deposit.service;

import pe.com.bootcamp.microservice.account.deposit.entity.Deposit;
import pe.com.bootcamp.microservice.account.deposit.model.Account;
import reactor.core.publisher.Mono;

public interface DepositService extends CrudService<Deposit, String> {
	Mono<Account> getAccount(String idAccount);
}

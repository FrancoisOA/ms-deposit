package pe.com.bootcamp.microservice.account.deposit.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.bootcamp.microservice.account.deposit.config.WebclientConfig;
import pe.com.bootcamp.microservice.account.deposit.entity.Deposit;
import pe.com.bootcamp.microservice.account.deposit.model.Account;
import pe.com.bootcamp.microservice.account.deposit.repository.IDepositRepository;
import pe.com.bootcamp.microservice.account.deposit.service.CalculationService;
import pe.com.bootcamp.microservice.account.deposit.service.DepositService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {

    @Autowired
    IDepositRepository depositRepo;

    private WebclientConfig webclient= new WebclientConfig();
    
    @Override
    public Flux<Deposit> findAll() {
    	log.info("Dentro de findAll");
    	return  depositRepo.findAll();
    } 

	@Override
	public Mono<Deposit> save(Deposit d) {
		d.setStatus(true);
		d.setDateTransaction(new Date());				
		return depositRepo.save(d).doOnSuccess(x -> {
			log.info("Dentro de doOnSuccess");
			x.setStatus(true);
			CalculationService ca = (amount, balance) ->  balance + amount;
			webclient.getAccount(x.getIdAccountDestiny())
			.switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)))
			.flatMap(f -> {
				f.setBalance(ca.Calcule(x.getAmount(), f.getBalance()));
				d.setInitialBalance(f.getBalance());
				log.info("Dentro de subscribe");
				return webclient.updateAccount(f);
			});
		});
	}
	
	 @Override
	    public Mono<Deposit> update(Deposit d) {
	        return  depositRepo.findById(d.getId())
	                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)))
	                .flatMap(o -> {
	                	if (d.getIdAccountDestiny() != null) {o.setIdAccountDestiny(d.getIdAccountDestiny());}
	                	if (d.getConcept() != null) {o.setConcept(d.getConcept());}
	                	if (d.getUser() != null) {o.setUser(d.getUser());}
	                	if (d.getAmount() != null) {o.setAmount(d.getAmount());}
	                    if (d.getCurrency() != null) {o.setCurrency(d.getCurrency());}
	                    if (d.getChannel() != null) {o.setChannel(d.getChannel());}
	                    if (d.getStatus() != null) {o.setStatus(d.getStatus());}
	                    return depositRepo.save(o);
	                });
	    }

	    @Override
	    public Mono<Deposit> deleteById(String id) {
	        return  depositRepo.findById(id)
	                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)))
	                .flatMap(o -> {
	                    o.setStatus(false);
	                    return depositRepo.save(o);
	                });
	    }

	    @Override
	    public Mono<Deposit> findById(String id) {
	        return depositRepo.findById(id);
	    }

	    @Override
	    public Mono<Account> getAccount(String idAccount) {
	        return webclient.getAccount(idAccount);
	    }
}

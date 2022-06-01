package pe.com.bootcamp.microservice.account.deposit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.bootcamp.microservice.account.deposit.entity.Deposit;
import pe.com.bootcamp.microservice.account.deposit.model.Account;
import pe.com.bootcamp.microservice.account.deposit.service.DepositService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path="/deposit")
public class DepositController {

    @Autowired
    DepositService depositService;
    
    
    @GetMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Deposit>getDeposit(){
        log.info("Metodo getDeposit");
        return depositService.findAll();
    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Deposit> saveDeposit(@RequestBody Deposit deposit){
    	log.info("Metodo saveDeposit");
        return depositService.save(deposit);
    }

    @GetMapping("/deposit/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Account>getDepositByAccount(@PathVariable String id){
    	log.info("Metodo getDepositByAccount");
        return depositService.getAccount(id);
    }
}
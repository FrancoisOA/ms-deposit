package pe.com.bootcamp.microservice.account.deposit.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bootcamp.microservice.account.deposit.entity.Deposit;

@Repository
public interface IDepositRepository extends ReactiveMongoRepository<Deposit, String>{
}

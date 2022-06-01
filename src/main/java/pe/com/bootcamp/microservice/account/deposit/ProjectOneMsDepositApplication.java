package pe.com.bootcamp.microservice.account.deposit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProjectOneMsDepositApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOneMsDepositApplication.class, args);
	}

}
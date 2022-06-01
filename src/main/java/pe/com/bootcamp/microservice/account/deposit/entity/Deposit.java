package pe.com.bootcamp.microservice.account.deposit.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document(collection = "tb_deposit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Deposit {
	@Id
	private String id;
 	private String idAccount;// id cuenta destino
 	private String concept;
 	private String user;
 	private Double Amount; //monto de transaccion
 	private String currency; //divisa
	private String channel; //canal
	private String codEmpl; //trabajador ventanilla

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateTransaction; //fecha transaccion	
	private Double initialBalance; //saldo inicial
	private Boolean status;
}
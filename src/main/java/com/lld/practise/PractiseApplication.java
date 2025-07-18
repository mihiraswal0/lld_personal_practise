package com.lld.practise;

import ch.qos.logback.core.net.server.Client;
import com.lld.practise.paymentGateway.entities.PaymentMode.BankClient;
import com.lld.practise.paymentGateway.enums.PaymentType;
import com.lld.practise.paymentGateway.service.BankClientManager;
import com.lld.practise.paymentGateway.service.PaymentGateway;
import com.lld.practise.paymentGateway.strategy.paymentRouting.CustomPaymentRoutingStrategy;
import com.lld.practise.paymentGateway.strategy.paymentRouting.IPaymentRouteStrategy;
import com.lld.practise.paymentGateway.strategy.paymentRouting.RandomPaymentRouting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootApplication
@EnableAsync
public class PractiseApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(PractiseApplication.class, args);

		IPaymentRouteStrategy iPaymentRouteStrategy=new CustomPaymentRoutingStrategy();
		BankClientManager bankClientManager=new BankClientManager(iPaymentRouteStrategy);
		PaymentGateway paymentGateway=new PaymentGateway(bankClientManager);

		bankClientManager.addBank(new BankClient(1,"HDFC"));
		bankClientManager.addBank(new BankClient(2,"ICIC"));
		bankClientManager.addPaymentMode(1, PaymentType.UPI,70);
		bankClientManager.addPaymentMode(2,PaymentType.UPI,30);

		Map<String,String> upiDetails=new HashMap<>();
		upiDetails.put("upiId","mihiraswal@gmail.com");

		for(int i=0;i<10;i++){
			paymentGateway.bookPayment(PaymentType.UPI,upiDetails);
			Thread.sleep(2000);
		}
//		Map<String,String> bankingDetails=new HashMap<>();
//		bankingDetails.put("userName","Mihir Aswal");
//		bankingDetails.put("password","Mihir");
//		paymentGateway.bookPayment(PaymentType.UPI,upiDetails);
//		paymentGateway.bookPayment(PaymentType.NETBANKING,bankingDetails);


	}

}

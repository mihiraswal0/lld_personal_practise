package com.lld.practise.paymentGateway.service;

import com.lld.practise.paymentGateway.PaymentModeFactory;
import com.lld.practise.paymentGateway.entities.PaymentMode.BankClient;
import com.lld.practise.paymentGateway.entities.PaymentMode.IPaymentMode;
import com.lld.practise.paymentGateway.entities.PaymentMode.UpiMode;
import com.lld.practise.paymentGateway.enums.PaymentType;
import lombok.Getter;

import java.util.Map;

@Getter
public class PaymentGateway {
    private final BankClientManager bankClientManager;
    public PaymentGateway(BankClientManager bankClientManager){
        this.bankClientManager=bankClientManager;
    }
    public void bookPayment(PaymentType paymentType, Map<String,String> details)
    {
        try {

            IPaymentMode paymentMode = PaymentModeFactory.getPaymentMode(paymentType, details);
            System.out.println("Payment mode created");
            boolean detailsValidated = paymentMode.vaildateDetails();
            if (!(detailsValidated)) {
                System.out.println("Payment failed because of insufficient details");
                return;
            }
            System.out.println("Payment mode details verified");
            BankClient bankClient = bankClientManager.getBankClient(paymentType);
            if(bankClient==null){
                System.out.println("No Bank avaialble for request");
                return;
            }
            System.out.println("Required bank client found: " + bankClient.getBankName());
            bankClient.performSuccessPayment(paymentMode);
            bankClient.performUnsuccessPayment(paymentMode);
        }
        catch (Exception e){
            System.out.println("Execption occured "+e.getMessage());
        }

    }

}

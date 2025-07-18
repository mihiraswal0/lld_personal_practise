package com.lld.practise.paymentGateway.service;

import ch.qos.logback.core.net.server.Client;
import com.lld.practise.paymentGateway.entities.PaymentMode.BankClient;
import com.lld.practise.paymentGateway.enums.PaymentType;
import com.lld.practise.paymentGateway.strategy.paymentRouting.IPaymentRouteStrategy;

import java.util.HashMap;
import java.util.Map;

public class BankClientManager {
    private final Map<Integer, BankClient> bankClientMap;
    private final IPaymentRouteStrategy paymentRouteStrategy;
    public BankClientManager(IPaymentRouteStrategy paymentRouteStrategy){
        bankClientMap=new HashMap<>();
       this.paymentRouteStrategy=paymentRouteStrategy;
    }
    public void addBank(BankClient bankClient){
        bankClientMap.put(bankClient.getBankId(),bankClient);
    }

    public void addPaymentMode(int bankId, PaymentType paymentType,int limit) {
        BankClient client = bankClientMap.get(bankId);
        if (client != null) {
            client.addPaymentMode(paymentType);
            paymentRouteStrategy.modifyBankTrafficLimits(paymentType, client, limit);
        }
    }


    public BankClient getBankClient(PaymentType paymentType){
       return paymentRouteStrategy.findBankClient(paymentType);
    }

}

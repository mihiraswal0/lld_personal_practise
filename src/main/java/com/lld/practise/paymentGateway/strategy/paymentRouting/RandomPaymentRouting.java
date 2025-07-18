package com.lld.practise.paymentGateway.strategy.paymentRouting;

import com.lld.practise.paymentGateway.entities.PaymentMode.BankClient;
import com.lld.practise.paymentGateway.enums.PaymentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RandomPaymentRouting implements IPaymentRouteStrategy{
    private final Map<PaymentType, Map<BankClient,Integer>> paymentBankListDetails;


    public RandomPaymentRouting() {
        this.paymentBankListDetails = new HashMap<>();
    }

    public boolean modifyBankTrafficLimits(PaymentType paymentType,BankClient bankClient,int newLimit){
        Map<BankClient,Integer>bankTraffic=paymentBankListDetails.computeIfAbsent(paymentType,paymentType1 -> new HashMap<>());
        bankTraffic.put(bankClient,newLimit);
        return  true;
    }

    @Override
    public BankClient findBankClient(PaymentType paymentType) {
        Map<BankClient,Integer> bankTrafficDetails=paymentBankListDetails.getOrDefault(paymentType,null);
        if(bankTrafficDetails==null) {
            System.out.println("No Bank Client exist for given payment mode ");
            throw new RuntimeException("No Bank Client exist for given payment mode");
        }
        int randomIdx=(int)(Math.random()*bankTrafficDetails.size());
        return  new ArrayList<>(bankTrafficDetails.keySet()).get(randomIdx);
    }
}

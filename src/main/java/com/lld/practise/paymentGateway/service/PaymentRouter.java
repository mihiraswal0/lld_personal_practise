package com.lld.practise.paymentGateway.service;

import com.lld.practise.paymentGateway.entities.PaymentMode.BankClient;
import com.lld.practise.paymentGateway.enums.PaymentType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRouter {
    Map<PaymentType, Map<BankClient,Integer>> paymentTrafficMap;
    public PaymentRouter(){
        paymentTrafficMap=new HashMap<>();
    }

    public BankClient findBankClient(PaymentType paymentType){
        Map<BankClient,Integer> bankTrafficDetails=paymentTrafficMap.getOrDefault(paymentType,null);
        if(bankTrafficDetails==null) {
            System.out.println("Failed to find the required bank client");
            throw new RuntimeException("Failed to find the required bank client");
        }
        int randomIdx=(int)(Math.random()*bankTrafficDetails.size());
        return  new ArrayList<>(bankTrafficDetails.keySet()).get(randomIdx);
    }
}

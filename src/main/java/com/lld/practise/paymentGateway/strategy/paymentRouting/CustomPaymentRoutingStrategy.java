package com.lld.practise.paymentGateway.strategy.paymentRouting;

import com.lld.practise.paymentGateway.entities.PaymentMode.BankClient;
import com.lld.practise.paymentGateway.enums.PaymentType;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomPaymentRoutingStrategy implements  IPaymentRouteStrategy{

    private final Map<PaymentType, Map<BankClient,Integer>> paymentBankListDetails;
    private final Map<PaymentType,Map<BankClient, AtomicInteger>> requestCountDetails;
    private final Map<PaymentType,Object> paymentLocks;
    private final int TOTAL_REQUEST=5;

    public CustomPaymentRoutingStrategy() {
        this.requestCountDetails = new HashMap<>();
        this.paymentBankListDetails = new HashMap<>();
        paymentLocks=new HashMap<>();
    }

    @Override
    public BankClient findBankClient(PaymentType paymentType) {
        Object lock = paymentLocks.computeIfAbsent(paymentType, paymentType1 -> new Object());
        synchronized (lock) {
            Map<BankClient, Integer> bankRequestLimits = paymentBankListDetails.computeIfAbsent(paymentType, paymentType1 -> new HashMap<>());
            Map<BankClient, AtomicInteger> bankRequestUsed = requestCountDetails.computeIfAbsent(paymentType, paymentType1 -> new HashMap<>());

            for (Map.Entry<BankClient, AtomicInteger> entry : bankRequestUsed.entrySet()) {
                int requestCount = entry.getValue().get();
                int requestLimit = (int) (bankRequestLimits.get(entry.getKey()) * TOTAL_REQUEST) / 100;
                System.out.println("Limits for bank: "+entry.getKey().getBankName()+" limit: "+requestLimit);
                if (requestCount + 1 <= requestLimit) {
                    bankRequestUsed.get(entry.getKey()).incrementAndGet();
                    return entry.getKey();
                }
            }
            return null;
        }
    }

    @Override
    public boolean modifyBankTrafficLimits(PaymentType paymentType, BankClient bankClient, int newLimits) {
        Map<BankClient,Integer> bankList=paymentBankListDetails.computeIfAbsent(paymentType,paymentType1 -> new HashMap<>());
        bankList.put(bankClient,newLimits);
        Map<BankClient,AtomicInteger>requestDetails=requestCountDetails.computeIfAbsent(paymentType,paymentType1 -> new HashMap<>());
        requestDetails.put(bankClient,new AtomicInteger(0));
        return true;
    }

     private static class  BankRequestInfo{
        BankClient bankClient;
        int weight;
        BankRequestInfo(BankClient bankClient,int weight){
            this.bankClient=bankClient;
            this.weight=weight;
        }
     }
}

package com.lld.practise.paymentGateway.strategy.paymentRouting;

import com.lld.practise.paymentGateway.entities.PaymentMode.BankClient;
import com.lld.practise.paymentGateway.enums.PaymentType;
import com.lld.practise.paymentGateway.service.BankClientManager;

public interface IPaymentRouteStrategy {

public BankClient findBankClient(PaymentType paymentType);
public boolean modifyBankTrafficLimits(PaymentType paymentType,BankClient bankClient,int newLimits);
}

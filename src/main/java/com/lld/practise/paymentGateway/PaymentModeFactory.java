package com.lld.practise.paymentGateway;

import com.lld.practise.paymentGateway.entities.PaymentMode.CardMode;
import com.lld.practise.paymentGateway.entities.PaymentMode.IPaymentMode;
import com.lld.practise.paymentGateway.entities.PaymentMode.NetBankingMode;
import com.lld.practise.paymentGateway.entities.PaymentMode.UpiMode;
import com.lld.practise.paymentGateway.enums.PaymentType;

import java.util.Map;

public class PaymentModeFactory {

    public static IPaymentMode getPaymentMode(PaymentType paymentType, Map<String,String> details){
        switch (paymentType){
            case UPI -> {
                return UpiMode.builder().upiId(details.getOrDefault("upiId",null)).build();
            }
            case CARD -> {
                return CardMode.builder().cardNumber(details.getOrDefault("cardNumber",null)).cardHolderName(details.getOrDefault("cardHolderName",null)).cVV(details.getOrDefault("cVV",null)).expiryTime(details.getOrDefault("expiryTime",null)).build();
            }
            case NETBANKING -> {
                return NetBankingMode.builder().userName(details.getOrDefault("userName",null)).password(details.getOrDefault("password",null)).build();
            }
            default -> {
               throw new IllegalArgumentException("Given Payment Type not implemented");
            }
        }
    }

}

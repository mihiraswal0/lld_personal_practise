package com.lld.practise.paymentGateway.entities.PaymentMode;

import com.lld.practise.paymentGateway.enums.PaymentType;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BankClient {
    private int bankId;
    private String bankName;
    private final List<PaymentType> paymentModeList;

    public BankClient(){
        paymentModeList=new ArrayList<>();
    }
    public BankClient(int bankId,String bankName){
        paymentModeList=new ArrayList<>();

        this.bankId=bankId;
        this.bankName=bankName;
    }
    public void addPaymentMode(PaymentType paymentType){
        paymentModeList.add(paymentType);
    }

    public void performSuccessPayment(IPaymentMode paymentMode){
        System.out.printf("Successfull payment: for bank client :%s and payment details are: %s %n",bankName,paymentMode.toString());
    }
    public void performUnsuccessPayment(IPaymentMode paymentMode){
        System.out.printf("Unsuccessfull payment: for bank client :%s and payment details are: %s %n",bankName,paymentMode.toString());

    }
}

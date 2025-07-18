package com.lld.practise.paymentGateway.entities.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetBankingMode implements IPaymentMode{
    private String userName;
    private String password;
    @Override
    public boolean vaildateDetails() {
        String errors="";

        if(userName==null)
        {
            errors+="User name not provided \n";
        }
        if(password==null){
            errors+="Password not provided \n";
        }

        if(!(errors.isEmpty()))
        {
            System.out.println("Errors "+errors);
        }
        return errors.isEmpty();
    }
}

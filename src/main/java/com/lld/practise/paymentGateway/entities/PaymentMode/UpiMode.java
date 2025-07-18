package com.lld.practise.paymentGateway.entities.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpiMode implements IPaymentMode{
    private String upiId;
    @Override
    public boolean vaildateDetails() {
        String errors="";
        if(upiId.isEmpty())
        {
            errors+="Upi id is not valid";
        }
        if(!(errors.isEmpty())){
            System.out.println("Errors: "+errors);
        }
        return errors.isEmpty();
    }
}

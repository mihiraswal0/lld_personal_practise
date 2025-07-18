package com.lld.practise.paymentGateway.entities.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardMode implements IPaymentMode{
    private String cardNumber;
    private String cVV;
    private String cardHolderName;
    private String expiryTime;
    @Override
    public boolean vaildateDetails() {
       String errors="";
       if(cardNumber==null || cardNumber.length()!=12 || cardNumber.matches("\\d{12}"))
       {
        errors+="Card number is not valid \n";
       }
       if(cVV==null || cVV.length()!=3 || cVV.contains("\\d{3}"))
       {
           errors+="CVV is in invalid format \n";
       }
       if(cardHolderName==null || cardNumber.isEmpty())
       {
           errors+="Card Holder details are not valid \n";
       }
       if(expiryTime==null
        || expiryTime.isEmpty()){
           errors+="Expiry time is invalid \n";
       }

       if(!(errors.isEmpty()))
       {
           System.out.println("Errors while validating details: "+errors);
//           it should throw exception
       }

       return errors.isEmpty();

    }

}

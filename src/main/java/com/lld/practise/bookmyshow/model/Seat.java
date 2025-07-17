package com.lld.practise.bookmyshow.model;

import com.lld.practise.bookmyshow.enums.PaymentStatus;
import com.lld.practise.bookmyshow.enums.SeatStatus;
import lombok.AllArgsConstructor;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Seat {
    private final UUID seatId;
    private final SeatStatus seatStatus;


    public Seat(UUID seatId){
        this.seatId=seatId;
        seatStatus=SeatStatus.AVAILABLE;
    }


}

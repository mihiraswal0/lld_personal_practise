package com.lld.practise.bookmyshow.model;

import com.lld.practise.bookmyshow.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Booking {
    private final UUID bookingId;
    private final User user;
    private final Show show;
    private final List<Seat> seatList;
    private final PaymentStatus paymentStatus;
    private final LocalDateTime localDateTime;
}

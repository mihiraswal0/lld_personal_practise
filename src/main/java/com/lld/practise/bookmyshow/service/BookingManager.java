package com.lld.practise.bookmyshow.service;

import com.lld.practise.bookmyshow.model.Booking;
import com.lld.practise.bookmyshow.model.Seat;
import com.lld.practise.bookmyshow.model.Show;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingManager {
    Map<UUID, Booking> bookingDetails;
    Map<UUID, Show> showDetails;
    Map<UUID,Object> showLocks;
    public BookingManager() {
        bookingDetails = new HashMap<>();
        showDetails = new HashMap<>();
    }

    public void addShow(Show show){
        showDetails.putIfAbsent(show.getShowId(),show);
    }

    public void createBooking(UUID showId, List<Seat> seatList)
    {
        Object lock=showLocks.computeIfAbsent(showId,l->{return new Object();});
        synchronized (lock){
//            boolean seatsavailable(seatList);
//            1.seatsavailable
//                2.bookseats
//                save booking
        }
    }

    public void cancelBooking(UUID bookingId){

    }
    public void confirmBooking(UUID bookingId){

    }

}

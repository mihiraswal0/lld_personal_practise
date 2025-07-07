package com.lld.practise.bookmyshow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
@Getter
public class Show {
    private final String showName;
    private final UUID showId;
    private final Map<UUID,Seat> seatList;

    public Show(UUID showId, String showName) {
        this.showName = showName;
        this.showId = showId;
        seatList=new HashMap<>();
    }

    public void addSeat(Seat seat){

    }

    public void removeSeat(Seat seat)
    {

    }

    public Map<UUID,Seat> getFreeSeats(Seat seat)
    {
        return seatList;
    }

}

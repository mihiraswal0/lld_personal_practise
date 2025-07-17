package com.lld.practise.Elevator.entities;

import com.lld.practise.Elevator.enums.Direction;
import com.lld.practise.Elevator.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LiftRequest {
    private int floor;
    private RequestType REQUEST_TYPE;
    private Direction direction;
}

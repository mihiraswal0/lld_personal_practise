package com.lld.practise.Elevator.strategy.ElevatorSelectionStrategy;

import com.lld.practise.Elevator.ElevatorService;
import com.lld.practise.Elevator.entities.LiftRequest;

import java.util.List;

public interface IElevatorStrategy {
    public ElevatorService requiredElevator(LiftRequest liftRequest, List<ElevatorService> elevatorServiceList);
}

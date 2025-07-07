package com.lld.practise.Elevator.strategy.ElevatorSelectionStrategy;

import com.lld.practise.Elevator.ElevatorService;
import com.lld.practise.Elevator.entities.LiftRequest;

import java.util.List;

public class RoundRobinSelection implements IElevatorStrategy {
    private int index;

    public RoundRobinSelection(){
        index=0;
    }

    public ElevatorService requiredElevator(LiftRequest liftRequest,List<ElevatorService> elevatorServiceList){
        synchronized (this) {
            ElevatorService requiredElevator = elevatorServiceList.get(index);
            index += 1;
            index %= elevatorServiceList.size();
            return requiredElevator;
        }
    }

}


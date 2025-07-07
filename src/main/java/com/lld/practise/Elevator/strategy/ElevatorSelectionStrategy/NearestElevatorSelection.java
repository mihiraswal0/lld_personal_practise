package com.lld.practise.Elevator.strategy.ElevatorSelectionStrategy;


import com.lld.practise.Elevator.ElevatorService;
import com.lld.practise.Elevator.entities.LiftRequest;

import java.util.List;

public class NearestElevatorSelection implements IElevatorStrategy {


    @Override
    public ElevatorService requiredElevator(LiftRequest liftRequest, List<ElevatorService> elevatorServiceList) {
        ElevatorService required=null;
        int minDistance=Integer.MAX_VALUE;
        for (ElevatorService elevator : elevatorServiceList) {
            int distance = elevator.getDistance(liftRequest);
            if (distance < minDistance) {
                minDistance = distance;
                required=elevator;
            }
        }
        return required==null?elevatorServiceList.get(0):required;
    }



}

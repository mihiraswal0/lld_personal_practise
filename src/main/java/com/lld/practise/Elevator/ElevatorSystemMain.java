package com.lld.practise.Elevator;

import com.lld.practise.Elevator.enums.Direction;
import com.lld.practise.Elevator.strategy.ElevatorSelectionStrategy.IElevatorStrategy;
import com.lld.practise.Elevator.strategy.ElevatorSelectionStrategy.NearestElevatorSelection;
import com.lld.practise.Elevator.strategy.ElevatorSelectionStrategy.RoundRobinSelection;
import com.lld.practise.Elevator.strategy.LiftSchedulerStrategy.FCFSScheduler;
import com.lld.practise.Elevator.strategy.LiftSchedulerStrategy.LookUpScheduler;

import java.sql.Time;

public class ElevatorSystemMain {
    public static void main(String args[]) throws InterruptedException {
        IElevatorStrategy elevatorStrategy=new RoundRobinSelection();
        ElevatorController elevatorController=new ElevatorController(elevatorStrategy);
        elevatorController.addElevator(1,new LookUpScheduler());
        ElevatorService elevatorService=elevatorController.addExternalRequest(2, Direction.UP);
        elevatorController.addExternalRequest(6,Direction.UP);
        elevatorController.addExternalRequest(4,Direction.UP);
        elevatorController.addExternalRequest(5,Direction.DOWN);
        Thread.sleep(5000);
        elevatorController.addInternalRequest(1,1);
//        elevatorController.addInternalRequest(elevatorService.getId(),8);
    }
}

package com.lld.practise.Elevator;

import com.lld.practise.Elevator.entities.LiftRequest;
import com.lld.practise.Elevator.enums.Direction;
import com.lld.practise.Elevator.enums.RequestType;
import com.lld.practise.Elevator.strategy.ElevatorSelectionStrategy.IElevatorStrategy;
import com.lld.practise.Elevator.strategy.LiftSchedulerStrategy.ILiftScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ElevatorController {
    Map<Integer,ElevatorService> elevatorDetails;
    IElevatorStrategy elevatorStrategy;

    public ElevatorController(IElevatorStrategy elevatorStrategy){
        this.elevatorStrategy=elevatorStrategy;
        elevatorDetails=new HashMap<>();

    }
    public void addElevator(int elevatorId, ILiftScheduler iLiftScheduler){
        ElevatorService elevatorService=new ElevatorService(elevatorId,iLiftScheduler, Direction.IDLE,0);
        elevatorDetails.put(elevatorId,elevatorService);
        Thread newElevatorThread=new Thread(elevatorService);
        newElevatorThread.start();
        System.out.println("Elevator service created successfully");
    }

    public ElevatorService addExternalRequest(int floor,Direction direction){
        LiftRequest liftRequest=new LiftRequest(floor, RequestType.EXTERNAL,direction);
        System.out.println("Next external request from floor: "+floor);
        ElevatorService requiredElevator=elevatorStrategy.requiredElevator(liftRequest,elevatorDetails.values().stream().toList());
        System.out.println("Chosen Elevator: "+requiredElevator.getId());
        requiredElevator.addRequest(liftRequest);
        return requiredElevator;
    }
    public void addInternalRequest(int elevatorId,int floor){
    LiftRequest liftRequest=new LiftRequest(floor,RequestType.INTERNAL,Direction.IDLE);
    System.out.println("Internal Request for floor: "+floor+"and elevator :"+elevatorId);
    ElevatorService elevatorService=elevatorDetails.get(elevatorId);
    elevatorService.addRequest(liftRequest);
    }


}

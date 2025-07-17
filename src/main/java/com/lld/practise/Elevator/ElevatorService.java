package com.lld.practise.Elevator;

import com.lld.practise.Elevator.entities.LiftRequest;
import com.lld.practise.Elevator.enums.Direction;
import com.lld.practise.Elevator.strategy.LiftSchedulerStrategy.ILiftScheduler;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
@Getter
public class ElevatorService implements Runnable{
    private final Integer id;
    private ILiftScheduler scheduler;
    private Direction liftDirection;
    private int floor;
    private final int MAX_FLOOR=10;

    public void addRequest(LiftRequest liftRequest){
    System.out.println("Request added to lift :"+id+"from floor "+liftRequest.getFloor());
    scheduler.addRequest(liftRequest,floor);
    }

    public void moveTo(int destinationFloor)  {
        try {
            while (floor != destinationFloor) {
                if (floor < destinationFloor) {
                    floor += 1;
                    liftDirection = Direction.UP;
                } else {
                    floor -= 1;
                    liftDirection = Direction.DOWN;
                }
                System.out.println("Elevator id: " + id + "moved to floor :" + floor);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void nextFloor() {
        try {
            while (true) {
                try {
                    System.out.println("Waiting for next floor request in elevator " + id);
                    int nextFloor = scheduler.getNextFloor(floor, liftDirection).get();
                    System.out.println("Next Floor picked:" + nextFloor + "by elevator " + id);
                    moveTo(nextFloor);
                }
                catch (ExecutionException ex){
                    if(ex.getCause() instanceof NoSuchElementException){
                        System.out.println("No pending requests. Elevator " + id + " is idle.");
                        liftDirection = Direction.IDLE;
                        Thread.sleep(2000); // wait before retrying
                    } else {
                        System.out.println("Unhandled exception in elevator " + id + ": " + ex.getCause().getMessage());
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
    public int getDistance(LiftRequest liftRequest) {
        if(liftRequest.getDirection()==liftDirection){
            if(liftDirection==Direction.UP){
                return liftRequest.getFloor()<floor?liftRequest.getFloor()-floor:(2*MAX_FLOOR-liftRequest.getFloor()-floor);
            }
            else {
                return floor+liftRequest.getFloor();
            }
        }
        else
        {
            if(liftDirection==Direction.UP){
                return (MAX_FLOOR-floor)+(MAX_FLOOR-liftRequest.getFloor());
            }
            else{
                return floor+liftRequest.getFloor();
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Elevator id:"+id+"started");
            nextFloor();
    }
}

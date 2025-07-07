package com.lld.practise.Elevator.strategy.LiftSchedulerStrategy;

import com.lld.practise.Elevator.entities.LiftRequest;
import com.lld.practise.Elevator.enums.Direction;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;

public class LookUpScheduler implements ILiftScheduler {
    PriorityBlockingQueue<Integer> upRequest;
    PriorityBlockingQueue<Integer> lowerRequest;


    public LookUpScheduler(){
        upRequest=new PriorityBlockingQueue<>();
        lowerRequest=new PriorityBlockingQueue<>(10, Comparator.reverseOrder());
    }
    @Override
    public void addRequest(LiftRequest liftRequest, int floor) {
        if(liftRequest.getFloor()<floor){
            lowerRequest.add(liftRequest.getFloor());
        }
        else{
            upRequest.add(liftRequest.getFloor());
        }
    }


    @Override
    public Future<Integer> getNextFloor(int floor, Direction liftDirection) {
       try{
           Integer nextFloor=null;
           if(liftDirection==Direction.UP){
               if(!(upRequest.isEmpty())){
                   nextFloor=upRequest.poll();

               }
               else if(!(lowerRequest.isEmpty()))
               {
                   nextFloor=lowerRequest.poll();
               }

           }
           else{
               if(!(lowerRequest.isEmpty())){
                   nextFloor=lowerRequest.poll();
               }
               else if(!(upRequest.isEmpty())){
                   nextFloor=upRequest.poll();
               }
           }
           if(nextFloor==null){
               CompletableFuture<Integer> empty = new CompletableFuture<>();
               empty.completeExceptionally(new NoSuchElementException("No pending requests"));
               return empty;
           }
           CompletableFuture<Integer> nextFloorFuture=new CompletableFuture<>();
           nextFloorFuture.complete(nextFloor);
           return nextFloorFuture;
       }
       catch (Exception e){
           System.out.println("Failed to get next floor "+e.getMessage());
           Thread.currentThread().interrupt();
       }
       return null;
    }
}

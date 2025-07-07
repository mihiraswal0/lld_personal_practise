package com.lld.practise.Elevator.strategy.LiftSchedulerStrategy;

import com.lld.practise.Elevator.entities.LiftRequest;
import com.lld.practise.Elevator.enums.Direction;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class FCFSScheduler implements  ILiftScheduler{
    BlockingQueue<Integer> queue;
    public FCFSScheduler(){
        queue=new LinkedBlockingQueue<>();
    }
    @Override
    public void addRequest(LiftRequest liftRequest, int floor) {
    queue.add(liftRequest.getFloor());
    }

    @Override
    public Future<Integer> getNextFloor(int floor, Direction liftDirection)  {
        try {
            int nextFloor=queue.take();
            CompletableFuture<Integer> response=new CompletableFuture<>();
            response.complete(nextFloor);
            return response;
        } catch (InterruptedException e) {
            System.out.println("Something went wrong while waiting for next floor"+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}


//public class FCFSScheduler implements ILiftScheduler {
//
//    private final Queue<CompletableFuture<Integer>> queue;
//
//    public FCFSScheduler() {
//        this.queue = new LinkedList<>();
//    }
//
//    @Override
//    public void addRequest(LiftRequest liftRequest, int floor) {
//        CompletableFuture<Integer> future = new CompletableFuture<>();
//        future.complete(floor);
//
//        synchronized (queue) {
//            queue.offer(future);
//            queue.notify(); // Wake up waiting threads (if any)
//        }
//    }
//
//    @Override
//    public Future<Integer> getNextFloor() {
//        synchronized (queue) {
//            while (queue.isEmpty()) {
//                try {
//                    queue.wait(); // Wait until an item is added
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                    CompletableFuture<Integer> failed = new CompletableFuture<>();
//                    failed.completeExceptionally(e);
//                    return failed;
//                }
//            }
//            return queue.poll(); // Return the next future
//        }
//    }
//}

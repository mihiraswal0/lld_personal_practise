package com.lld.practise.Elevator.strategy.LiftSchedulerStrategy;

import com.lld.practise.Elevator.entities.LiftRequest;
import com.lld.practise.Elevator.enums.Direction;

import java.util.concurrent.Future;

public interface ILiftScheduler {
    void addRequest(LiftRequest liftRequest, int floor);

    Future<Integer> getNextFloor(int floor, Direction liftDirection);
}

package com.nice2h8u.elevator_client_server_app.Entity.States;

import com.nice2h8u.elevator_client_server_app.Entity.Floor;

public class Staing extends ElevatorState {
    public Staing(Floor currentFloor) {
        super(currentFloor);
    }

    public String toString() {
        return "Лифт не покидает " +
                currentFloor.num() +
                " этаж";
    }
}

package com.nice2h8u.elevator_client_server_app.Entity.States;

import com.nice2h8u.elevator_client_server_app.Entity.Floor;

public class Closing extends ElevatorState {
    public Closing(Floor currentFloor) {
        super(currentFloor);
    }

    @Override
    public String toString() {
        return "Лифт покидает " +
                currentFloor.num() +
                " этаж, двери закрываются!";
    }
}

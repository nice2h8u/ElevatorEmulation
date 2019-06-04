package com.nice2h8u.elevator_client_server_app.Entity.States;

import com.nice2h8u.elevator_client_server_app.Entity.Floor;

public class Opening extends ElevatorState {
    public Opening(Floor currentFloor) {
        super(currentFloor);
    }

    public String toString() {
        return "Лифт приехал на " +
                currentFloor.num() +
                " этаж, двери открываются!";
    }
}

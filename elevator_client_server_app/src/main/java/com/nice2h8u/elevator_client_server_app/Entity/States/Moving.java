package com.nice2h8u.elevator_client_server_app.Entity.States;

import com.nice2h8u.elevator_client_server_app.Entity.Floor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Moving extends ElevatorState {

    private Floor goTo;


    public Moving(Floor currentFloor, Floor goTo) {
        super(currentFloor);
        this.goTo = goTo;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        Moving moving = (Moving) o;
        if ((super.currentFloor == moving.currentFloor) && (goTo == moving.goTo))
            return true;
        return false;
    }

    public String toString() {
        return "Лифт покидает " +
                currentFloor.num() +
                " этаж и едет на " + goTo.num() + " этаж";
    }

}

package com.nice2h8u.elevator_client_server_app.Entity.States;

import com.nice2h8u.elevator_client_server_app.Entity.Floor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ElevatorState {
    protected Floor currentFloor;

    public ElevatorState(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElevatorState that = (ElevatorState) o;

        return currentFloor == that.currentFloor;
    }

    @Override
    public int hashCode() {
        return currentFloor != null ? currentFloor.hashCode() : 0;
    }
}

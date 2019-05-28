package Objects;

import Enums.ElevatorStatus;
import Enums.Floor;

public class ResponseFromServer {

    public ResponseFromServer() {
    }

    public ResponseFromServer(ElevatorStatus elevatorStatus, Floor goToFloor, Floor currentFloor) {
        this.elevatorStatus = elevatorStatus;
        this.goToFloor = goToFloor;
        this.currentFloor = currentFloor;
    }

    private ElevatorStatus elevatorStatus;
    private Floor goToFloor;
    private Floor currentFloor;

    public ElevatorStatus getElevatorStatus() {
        return elevatorStatus;
    }

    public void setElevatorStatus(ElevatorStatus elevatorStatus) {
        this.elevatorStatus = elevatorStatus;
    }

    public Floor getGoToFloor() {
        return goToFloor;
    }

    public void setGoToFloor(Floor goToFloor) {
        this.goToFloor = goToFloor;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }
}

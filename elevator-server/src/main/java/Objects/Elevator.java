package Objects;

import Enums.ElevatorStatus;
import Enums.Floor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Elevator {

    private static volatile Elevator ourInstance = new Elevator();
    private List<Command> elevatorPath = Collections.synchronizedList(new ArrayList<Command>());
    private ElevatorStatus elevatorStatus;
    private Floor goToFloor;
    private Floor currentFloor;

    public static synchronized Elevator getInstance() {
        return ourInstance;
    }

    private Elevator() {
        elevatorStatus = ElevatorStatus.Staing;
        goToFloor = Floor.First;
        currentFloor = Floor.First;
    }

    public List<Command> getElevatorPath() {
        return elevatorPath;
    }

    public void setElevatorPath(List<Command> elevatorPath) {
        this.elevatorPath = elevatorPath;
    }

    public void setElevatorPath(ArrayList<Command> elevatorPath) {
        this.elevatorPath = elevatorPath;
    }

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

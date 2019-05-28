package Enums;

public enum ElevatorStatus {
    Moving("Движется"), Opening("Двери открываются"), Closing("Двери закрываются"), Staing("Лифт не покидает");

    private String elevatorStatus;

    ElevatorStatus(String elevatorStatus) {
        this.elevatorStatus = elevatorStatus;
    }

    @Override
    public String toString() {
        return  elevatorStatus ;

    }
}

package com.nice2h8u.elevator_client_server_app.Entity;



import com.nice2h8u.elevator_client_server_app.DTO.CommandDTO;
import com.nice2h8u.elevator_client_server_app.Entity.States.ElevatorState;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Elevator {
    private static Elevator ourInstance = new Elevator();//mb violite

    private Floor currentFloor;
    private List<Command> commandsOfElevator;
    private String currentState;
    private Boolean openOrClosing;

    public static Elevator getInstance() {
        return ourInstance;
    }

    private Elevator() {

        currentFloor = Floor.First;
        commandsOfElevator = new ArrayList<>();
    }

    public void addCommandDto(CommandDTO commandDTO) {
        Floor goToFloor = null;
        for (Floor floor : Floor.values()) {
            if (floor.toString().equals(commandDTO.getState())) {
                goToFloor = floor;
                break;
            }
        }
        commandsOfElevator.add(Command.builder()
                .timeOfCommand(new Date())
                .goToFloor(goToFloor)
                .build());

    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(Floor currentFloor) {
        this.currentFloor = currentFloor;
    }

    public List<Command> getCommandsOfElevator() {
        return commandsOfElevator;
    }

    public void setCommandsOfElevator(List<Command> commandsOfElevator) {
        this.commandsOfElevator = commandsOfElevator;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public Boolean getOpenOrClosing() {
        return openOrClosing;
    }

    public void setOpenOrClosing(Boolean openOrClosing) {
        this.openOrClosing = openOrClosing;
    }
}

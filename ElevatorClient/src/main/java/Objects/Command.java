package Objects;

import Enums.Floor;

public class Command {

    public Command() {
    }

    public Command(Floor floorNumber, boolean isCommand, boolean isCommandFromElevator) {
        this.floorNumber = floorNumber;
        this.command = isCommand;
        this.commandFromElevator = isCommandFromElevator;
    }

    private Floor floorNumber;
    private Boolean command;
    private Boolean commandFromElevator;

    public Floor getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Floor floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Boolean getCommand() {
        return command;
    }

    public void setCommand(Boolean command) {
        this.command = command;
    }

    public Boolean getCommandFromElevator() {
        return commandFromElevator;
    }

    public void setCommandFromElevator(Boolean commandFromElevator) {
        this.commandFromElevator = commandFromElevator;
    }
}

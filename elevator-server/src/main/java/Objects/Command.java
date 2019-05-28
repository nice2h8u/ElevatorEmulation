package Objects;

import Enums.Floor;

public class Command {

    public Command() {
    }

    private Floor floorNumber;
    private Boolean commandFromElevator;
    private Boolean command;

    public Boolean getCommandFromElevator() {
        return commandFromElevator;
    }

    public Boolean getCommand() {
        return command;
    }

    public void setCommand(Boolean command) {
        this.command = command;
    }

    public Floor getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Floor floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setCommandFromElevator(Boolean commandFromElevator) {
        this.commandFromElevator = commandFromElevator;
    }
}

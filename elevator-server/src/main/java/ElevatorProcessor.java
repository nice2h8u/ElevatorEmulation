import Enums.ElevatorStatus;
import Enums.Floor;
import Objects.Command;
import Objects.Elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorProcessor implements Runnable {

    private final long DOOR_OPEN_DELAY = 2000;
    private final long DOOR_CLOSE_DELAY = 2000;
    private final long PATH_TO_FLORE_DELAY = 10000;

    public ElevatorProcessor() {

    }

    public void run() {
        Elevator elevator = Elevator.getInstance();

        while (true) {


            while (elevator.getElevatorPath().size() > 0) {
                try {


                    switch (elevator.getElevatorStatus()) {
                        case Moving:
                            System.out.println("Лифт покидает " + "  " + elevator.getCurrentFloor() + " этаж и едет на " + elevator.getGoToFloor());
                            Floor nextFloor = findNextFloor(elevator.getCurrentFloor(), elevator.getGoToFloor());
                            Thread.sleep(PATH_TO_FLORE_DELAY);
                            elevator.setCurrentFloor(nextFloor);
                            if (isNextFloorInCommandList(nextFloor, elevator.getElevatorPath()))
                                if (elevator.getElevatorPath().size() == 0) {//если лифт приехал на конечную
                                    elevator.setElevatorStatus(ElevatorStatus.Opening);
                                    Thread.sleep(DOOR_OPEN_DELAY);
                                    elevator.setElevatorStatus(ElevatorStatus.Staing);
                                } else elevator.setElevatorStatus(ElevatorStatus.Opening);

                            break;
                        case Staing:
                            System.out.println(elevator.getElevatorStatus() + "  " + elevator.getCurrentFloor() + " этаж. ");
                            elevator.setElevatorStatus(ElevatorStatus.Closing);
                            break;
                        case Closing:

                            elevator.setGoToFloor(elevator.getElevatorPath().get(0).getFloorNumber());
                            if (findNextFloor(elevator.getCurrentFloor(), elevator.getGoToFloor()) != (elevator.getCurrentFloor())) {
                                System.out.println("Лифт покидает " + elevator.getCurrentFloor() + " этаж. " + elevator.getElevatorStatus());
                                Thread.sleep(DOOR_CLOSE_DELAY);
                                elevator.setElevatorStatus(ElevatorStatus.Moving);
                            }
                            else elevator.getElevatorPath().remove(0);
                            break;
                        case Opening:
                            System.out.println("Лифт приехал на " + elevator.getCurrentFloor() + " этаж. " + elevator.getElevatorStatus());
                            Thread.sleep(DOOR_OPEN_DELAY);
                            elevator.setElevatorStatus(ElevatorStatus.Closing);
                            break;
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
            elevator.setElevatorStatus(ElevatorStatus.Staing);
        }
    }


    private Floor findNextFloor(Floor currentFloor, Floor goTo) {
        Floor[] floors = Floor.values();
        Floor nextFloor = currentFloor;
        //если пункт назначения выше текущего этажа
        if (goTo.ordinal() - currentFloor.ordinal() > 0)
            nextFloor = floors[currentFloor.ordinal() + 1]; //следущий этаж ++
        else if (goTo.ordinal() - currentFloor.ordinal() < 0)//если ниже
            nextFloor = floors[currentFloor.ordinal() - 1];//следущий этаж --
        else if (goTo.ordinal() - currentFloor.ordinal() == 0)
            nextFloor = currentFloor;

        return nextFloor;
    }

    private boolean isNextFloorInCommandList(Floor nextFloor, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getFloorNumber() == nextFloor) {

                commands.remove(i);
                return true;
            }

        }

        return false;
    }

}

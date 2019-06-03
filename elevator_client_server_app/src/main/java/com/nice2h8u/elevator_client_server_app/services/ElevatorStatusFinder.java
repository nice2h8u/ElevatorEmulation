package com.nice2h8u.elevator_client_server_app.services;

import com.nice2h8u.elevator_client_server_app.Entity.Command;
import com.nice2h8u.elevator_client_server_app.Entity.Elevator;
import com.nice2h8u.elevator_client_server_app.Entity.Floor;
import com.nice2h8u.elevator_client_server_app.Entity.States.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ElevatorStatusFinder {

    private final long CLOSING_DELAY = 500;
    private final long OPENING_DELAY = 500;
    private final long MOVING_DELAY = 500;

    public ElevatorState getElevatorCurrentState(Date currentDate){
        List<Command> commands = new ArrayList<>();
        Elevator.getInstance().getCommandsOfElevator().forEach
                (command -> commands.add(Command.builder()
                        .goToFloor(command.getGoToFloor())
                        .timeOfCommand(command.getTimeOfCommand())
                        .build()));
        ElevatorState state = go(currentDate);


       // Elevator.getInstance().setCurrentFloor(Floor.First);
        Elevator.getInstance().setCommandsOfElevator(commands);

        return state;
    }
    private ElevatorState go (Date currentDate) {

        long currentTime = currentDate.getTime();
        long allTimeForActivities = 0;
        Command currentCommand = null;
        Elevator elevator = Elevator.getInstance();
        Floor nextFloor = null;
        List<Command> commands = elevator.getCommandsOfElevator();



        if (commands.size() == 0)
            return new Staing(elevator.getCurrentFloor());
        allTimeForActivities = commands.get(0).getTimeOfCommand().getTime();
        while (commands.size() != 0) {
            currentCommand = commands.get(0);


            if (currentCommand.getTimeOfCommand().getTime() > currentTime)
                return new Staing(elevator.getCurrentFloor());

            do {
                nextFloor = findNextFloor(elevator.getCurrentFloor(), currentCommand.getGoToFloor());//ищем куда ехать дальше
                if ((nextFloor==elevator.getCurrentFloor())){
                    commands.remove(currentCommand);
                    break;
                }

                //если лифт движется и момент времени совпадает то кул
                if (isTheTimeInRange(currentTime, allTimeForActivities, MOVING_DELAY)) {


                    return new Moving(elevator.getCurrentFloor(), nextFloor);

                }
                allTimeForActivities += MOVING_DELAY;
                elevator.setCurrentFloor(nextFloor);

                //ищем в командах все этажи на котрые приехали и если они есть проверяем время,
                // если совпадает, то удаляем из списка
                if (isCommandInList(elevator.getCurrentFloor(), commands, allTimeForActivities)) {
                    //если двери открываются и момент времени статуса совпадает то кул
                    if (isTheTimeInRange(currentTime, allTimeForActivities, OPENING_DELAY)) {

                        return new Opening(elevator.getCurrentFloor());
                    }
                    allTimeForActivities += OPENING_DELAY;

                    //если двери закрываются и момент времени статуса совпадает то кул
                    if (isTheTimeInRange(currentTime, allTimeForActivities, CLOSING_DELAY)) {

                        return new Closing(elevator.getCurrentFloor());
                    }
                    System.out.println((new Closing(elevator.getCurrentFloor())));
                    allTimeForActivities += CLOSING_DELAY;
                }


            } while (nextFloor != elevator.getCurrentFloor());

        }
        return new Staing(elevator.getCurrentFloor());
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


    private boolean isTheTimeInRange(long currentTime, long allTime, long delay) {
        if ((allTime + delay >= currentTime) && (currentTime >= allTime))
            return true;
        return false;
    }


    private boolean isCommandInList(Floor currentFloor, List<Command> commands, long timeForAllActivities) {

        boolean flag = false;

        Iterator<Command> iter = commands.iterator();
        while (iter.hasNext()) {
            Command command = iter.next();
            if (command.getGoToFloor() == currentFloor)
                if (command.getTimeOfCommand().getTime() <= timeForAllActivities) {

                    flag = true;
                    iter.remove();
                }
        }

        return flag;
    }
}

import Enums.ElevatorStatus;
import Enums.Floor;
import Objects.Command;
import Objects.Elevator;
import Objects.ResponseFromServer;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ElevatorServer implements Runnable {

    private Socket socket;

    private ObjectMapper mapper;


    public ElevatorServer(Socket socket) {
        this.socket = socket;
        mapper = new ObjectMapper();
        mapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        System.out.println("New client connected");

        //  run()
    }

    public void run() {
        String word;
        Elevator elevator = Elevator.getInstance();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);) {

            while (true) {
                word = in.readLine();


                if (word != null) {


                    Command command = deserializeJson(word);

                    if (!command.getCommand()) {

                        ElevatorStatus status = elevator.getElevatorStatus();

                        switch (status) {
                            case Opening:
                                out.println("Лифт приехал на " + elevator.getCurrentFloor() + " этаж. " + status.toString());
                                break;
                            case Closing:
                                out.println("Лифт покидает " + elevator.getCurrentFloor() + " этаж. " + status.toString());
                                break;
                            case Staing:
                                out.println(status + "  " + elevator.getCurrentFloor() + " этаж. ");
                                break;
                            case Moving:
                                out.println("Лифт покидает " + "  " + elevator.getCurrentFloor() + " этаж и едет на " + elevator.getGoToFloor());
                                break;
                        }
                    } else elevator.setElevatorPath(addElemIntoArray(elevator.getElevatorPath(), command));

                }
            }



        } catch (IOException e) {

        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    private Command deserializeJson(String json) {
        Command message = null;
        try {
            message = mapper.readValue(json, Command.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    private List<Command> addElemIntoArray(List<Command> elevatorPath, Command addedCommand) {
        int flag = 1;
        int endOfHighPriority = 0;
        if (!addedCommand.getCommandFromElevator()) {//если команда не из лифта
            for (Command temp : elevatorPath)
                if (temp.getFloorNumber() == addedCommand.getFloorNumber()) { //если номер этажа в списке=пришедшей команде
                    flag = 0;//добавлять в список не надо
                    break;
                }
            if (flag == 1)
                elevatorPath.add(addedCommand);
        } else if (elevatorPath.size() != 0) {//если команда из лифта
            for (int i = 0; i < elevatorPath.size(); i++) { //смотрим где заканчиваются команды из лифта
                if (elevatorPath.get(i).getCommandFromElevator())
                    endOfHighPriority = i + 1;
                else break;
            }

            for (int i = 0; i < elevatorPath.size(); i++) {
                if (elevatorPath.get(i).getFloorNumber() == addedCommand.getFloorNumber()) {//если номер этажа равен добавляемому этажу
                    flag = 0;


                    if (!elevatorPath.get(i).getCommandFromElevator()) {

                        elevatorPath.remove(i);
                        elevatorPath = pasteIntoList(elevatorPath, addedCommand, endOfHighPriority);
                    }
                }
            }
            if (flag == 1) {
                elevatorPath = pasteIntoList(elevatorPath, addedCommand, endOfHighPriority);

            }


        } else {
            elevatorPath.add(addedCommand);

        }
        return elevatorPath;
    }

    private ArrayList<Command> pasteIntoList(List<Command> elevatorPath, Command addedCommand, int index) {
        ArrayList<Command> tempArray = new ArrayList<>();

        if (index == 0) {
            tempArray.add(addedCommand);
            tempArray.addAll(elevatorPath);
        } else {
            for (int i = 0; i < index; i++) {
                tempArray.add(elevatorPath.get(i));
            }

            tempArray.add(addedCommand);
            for (int i = index; i < elevatorPath.size(); i++) {
                tempArray.add(elevatorPath.get(i));
            }


        }
        return tempArray;
    }


}

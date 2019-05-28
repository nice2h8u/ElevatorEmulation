import Enums.Floor;
import Objects.Command;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSocketSend implements Runnable {

    private ObjectMapper objectMapper;

    private Scanner in;


    ClientSocketSend() {
        objectMapper = new ObjectMapper();
        objectMapper.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
        in = new Scanner(System.in);


    }

    @Override
    public void run() {
        String word;

        try (Socket socket = new Socket("localhost", 20205);
             PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            while (true) {


                String readLine = in.nextLine();
                if (isTheStringCorrect(readLine)) {
                    Command command = getCommandFromString(readLine);
                    toServer.println(objectMapper.writeValueAsString(command));
                    if (!command.getCommand()) {
                        word = fromServer.readLine();

                        System.out.println(word);
                    }
                }



            }


        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }


    public Command getCommandFromString(String readLine) {
        Floor floorToGo = null;
        if (readLine.equals("st"))
            return new Command(Floor.Fifth, false, true);
        else if ((readLine.charAt(0) == 'c') && ((readLine.charAt(1) > 48) && (readLine.charAt(1) < 56))) {

            for (Floor temp : Floor.values())
                if (temp.ordinal() + 1 == Integer.parseInt(readLine.substring(1, 2)))
                    floorToGo = temp;
            return new Command(floorToGo, true, false);
        } else if ((readLine.charAt(0) > 48) && (readLine.charAt(0) < 56)){
            for (Floor temp : Floor.values())
                if (temp.ordinal() + 1 == Integer.parseInt(readLine.substring(0, 1)))
                    floorToGo = temp;
            return new Command(floorToGo, true, true);
        }
        return new Command();
    }

    public boolean isTheStringCorrect(String readLine){
        if (readLine.equals("st"))
            return true;
        if ((readLine.charAt(0) == 'c') && ((readLine.charAt(1) > 48) && (readLine.charAt(1) < 56)))
            return true;
        if ((readLine.charAt(0) > 48) && (readLine.charAt(0) < 56))
            return true;
        return false;
    }
}


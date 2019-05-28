import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new ElevatorProcessor());

        try (ServerSocket serverSocket = new ServerSocket(20205)) {


            executorService.execute(new ElevatorServer(serverSocket.accept()));


        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }

    }


}

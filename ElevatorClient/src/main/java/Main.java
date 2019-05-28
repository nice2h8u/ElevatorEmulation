import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ExecutorService executorService = Executors.newFixedThreadPool(2);


        executorService.execute(new ClientSocketSend());


    }
}

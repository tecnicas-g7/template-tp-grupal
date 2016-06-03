import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * Created by fran on 03/06/16.
 */

public class Sender implements Runnable {

    private Socket socket;
    private boolean running;

    public Sender(Socket socket) {
        this.socket = socket;
        this.running = true;
    }

    @Override
    public void run() {
        try {
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
            String inputClient = inFromUser.readLine();
            try {
                while (inputClient != null && !inputClient.equals("exit game")) {
                    outToServer.writeBytes(inputClient + '\n');
                    inputClient = inFromUser.readLine();
                }
            } catch (Exception e) {
                if (running) {
                    System.out.println("Error with connection...");
                }
            }
        } catch (IOException e) {
            //?
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        running = false;
    }
}

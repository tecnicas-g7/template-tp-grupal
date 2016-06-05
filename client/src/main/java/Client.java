import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by ezequiel on 2
 * 0/04/16.
 */
public class Client {

    private static boolean running;

    public static void main(String[] argv) throws Exception {
        System.out.println("This is the Client");
        String input;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        System.out.println("This is menu");
        input = inFromUser.readLine();
        while (input != null && !input.equals("exit")) {
            String[] message = input.split(" ");
            if (message[0].equals("connect")) {
                String[] address = message[1].split(":");
                Socket socket = new Socket(address[0], Integer.parseInt(address[1]));

                play(socket);
            }
            System.out.println("This is menu");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            input = inFromUser.readLine();
        }
    }

    private static void play(Socket socket) throws IOException {
        Sender sender = new Sender(socket);
        new Thread(sender).start();
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        System.out.println(inFromServer.readLine());
        running  = true;
        while (running) {
            try {
                listen(socket,inFromServer,sender);
            } catch (Exception e) {
                System.out.println("Error with connection...");
                running = false;
            }
        }
        socket.close();
    }

    private static void listen(Socket socket, BufferedReader inFromServer, Sender sender) throws IOException {
        String serverMessage = inFromServer.readLine();
        if (serverMessage != null && serverMessage.equals("W")) {
            System.out.println("Ganaste :)");
            sender.close();
            socket.close();
            running = false;
        } else {
            if (serverMessage != null && serverMessage.equals("L")) {
                System.out.println("Perdiste :(");
                sender.close();
                socket.close();
                running = false;
            }
        }
        if (serverMessage == null) {
            running = false;
            System.out.println("Error with connection...");
            return;
        }
        System.out.println(serverMessage);
    }
}


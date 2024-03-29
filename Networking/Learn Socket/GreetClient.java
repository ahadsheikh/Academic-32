import java.io.*;
import java.net.*;

public class GreetClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
 
    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
 
    public String sendMessage(String msg) {
        try {
            out.println(msg);
            String resp = in.readLine();
            return resp;
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
 
    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        GreetClient greetClient = new GreetClient();
        greetClient.startConnection("localhost",6666);
        System.out.println(greetClient.sendMessage("hello server")); 
        greetClient.stopConnection();
    }
}
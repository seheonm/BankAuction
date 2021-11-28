package Bank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Server for the bank; accepts connections from the both auction houses and agents.
 */
public class BankServer{

    /**
     * Instantiates a new Bank server.
     */
    public BankServer() {


        boolean isConnected = true;
        int portNumber= 55555;
        try {
            System.out.println("creating server socket.");
            ServerSocket s_sock = new ServerSocket(portNumber);
            Bank bank = new Bank(portNumber);
            while (isConnected) {
                Socket b_sock = s_sock.accept(); //Connection from agent or auction house
                ObjectOutputStream out = new ObjectOutputStream(b_sock.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(b_sock.getInputStream());
                ClientHandler gClient = new ClientHandler(b_sock, out, in,bank,true);
                //Bank Thread to start server
                Thread t = new Thread(gClient);
                t.start();
            }
        } catch (Exception e) {
            isConnected = false;
        }

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new BankServer();
    }
}


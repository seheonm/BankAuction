/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */
package AH;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClientTest is used to connect to auction house server and grab input
 */
public class ClientTest {
    /**
     * ClientTest constructor is used to connect
     * to auction house server and grab input
     */
    public ClientTest() throws IOException {
        System.out.println("Connecting to Auction House Server");
        Socket sock = new Socket("localhost", 55555);
        boolean run = true;
        Scanner s = new Scanner(System.in);
        ObjectOutputStream output = new
                ObjectOutputStream(sock.getOutputStream());
        ObjectInputStream input = new
                ObjectInputStream(sock.getInputStream());
        System.out.println("Connection Successful boiiiiii");
        while(run){
            System.out.println("Enter input:");
            String message = s.nextLine();
            if(message.equals("Complete"));
            break;
        }
    }
}
package AH;

import Messages.AuctionHouseMessage;
import Messages.BankMessage;
//import sun.security.x509.IPAddressName;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

import static Messages.AuctionHouseActions.AUCTION_REGISTER;

/**
 * AuctionHouseServer creates the Auction House Server
 */
public class AuctionHouseServer {
    private ServerSocket ahs_sock;
    private AuctionHouse AH;
    private ObjectOutputStream bankOut;
    private ObjectInputStream bankIn;
    private int port = 55555;
    private String IP;


    /**
     * AuctionHouseServer constructor create the Auction House Server
     */
    public AuctionHouseServer(int auctionHousePort, String ip){
        IP = ip;

        try {
            System.out.println("creating Auction House server socket.");
            ahs_sock = new ServerSocket(auctionHousePort);
            AH = new AuctionHouse(IP, auctionHousePort, "resources/items.txt");

            connectionThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Safely handles socket connections, tries again on failure
     * @param ip
     * @param port
     * @return
     * @throws UnknownHostException
     * @throws IOException
     */
    private Socket safeConnect(String ip,int port)throws UnknownHostException,IOException{
        Socket s = null;
        boolean connected = false;
        while(!connected) {
            try{
                s = new Socket(ip, port);
                connected = true;
                System.out.println("Connection successful");
                return s;

            }catch (ConnectException e) {
                System.out.println("Connect failed, trying again");
            }
            try{
                Thread.sleep(2000);
            }catch (InterruptedException ie){}
        }
        System.out.println("Failure");
        return  null;
    }

    private static int validateSocket(Scanner sc,
                                      String prompt, String error) {
        int port;
        do {
            System.out.println(prompt);
            while (!sc.hasNextInt()) {
                System.out.println(error);
                sc.next();
            }
            port = sc.nextInt();
        } while (port >= 6000 || port <= 5000);

        return port;
    }

    /**
     * connectionThread creates a connection thread
     */
    private void connectionThread(){
        //Connect and register with the bank to setup a new account
        // and be added to the list of auction houses
        try{
            Socket bankSocket = safeConnect(IP, port);
            System.out.println("Connected to the Bank");
            bankOut= new ObjectOutputStream(bankSocket.getOutputStream());
            bankIn = new ObjectInputStream(bankSocket.getInputStream());

            AuctionHouseMessage registerAH = new AuctionHouseMessage(AUCTION_REGISTER,
                    AH,"");
//            bankOut.writeObject(registerAH);
            bankOut.writeUnshared(registerAH);
            try{
                BankMessage message = (BankMessage)bankIn.readUnshared();
                System.out.println(message.getReply());
                AH.setAuctionID(message.getAccountNumber());
            }catch (ClassNotFoundException e){e.printStackTrace();}
            catch (Exception e){e.printStackTrace();}

            //flush output
            bankOut.flush();


        }catch (IOException e ){e.printStackTrace();}

        //Create a thread to listen in on agent connections
        Thread listener = new Thread(()->{
            try {
                while(true){
                    System.out.println("waiting for agent connection");
                    Socket ah_sock = ahs_sock.accept(); //Connection from agent or auction house
                    ObjectOutputStream agentOut= new ObjectOutputStream(ah_sock.getOutputStream());
                    ObjectInputStream agentIn= new ObjectInputStream(ah_sock.getInputStream());
                    System.out.println("Agent has connected to this server");
                    AuctionClient aClient = new AuctionClient(ah_sock, agentOut, agentIn,bankOut,bankIn,AH);
                    Thread thread = new Thread(aClient);
                    System.out.println("starting thread.");
                    thread.start();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        });
        listener.start();

    }

    /**
     * main starts the program
     * @param args
     */
    public static void main(String[] args) {
//        System.out.println("Please type in the bank's IP address:");
        Scanner scan = new Scanner(System.in);
//        System.out.println("Please enter your IP");
//        String ip = scan.nextLine();
//        scan.next();
        System.out.println("Enter the Bank's IP:");
        String IPprompt = scan.nextLine();
//        String prompt = "Please enter a valid port number (Between 5000 and 6000)";
//        String error = "Invalid port number, please enter a valid port number between 5000 and 6000";

//        int validated = validateSocket(scan, prompt, error);
        System.out.println("Enter port to run on:");
        int port = scan.nextInt();
        new AuctionHouseServer(port, IPprompt);
    }
}

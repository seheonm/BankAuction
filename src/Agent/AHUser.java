package Agent;

import AH.AgentClient;
import AH.AuctionHouse;
import AH.Item;
import Messages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Messages.AgentActions.AGENT_UPDATE_AUCTION;


/**
 * AHTester is a Test server for Auction House
 */
public class AHUser {
    ObjectOutputStream auctionOut;
    ObjectInputStream auctionIn;
    ObjectOutputStream bankOut;
    ObjectInputStream bankIn;
    Agent agent;
    Socket bankSocket;
    List<Item> items;
    int auctionHouseID;
    int PORT = 5555;
    String IP;


    public void refreshConnection(Socket socket) throws IOException {
        bankOut.close();
        bankIn.close();
        socket.close();
        socket = new Socket(IP, PORT);
        bankOut = new ObjectOutputStream(socket.getOutputStream());
        bankIn = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * AHUser is a User server for Auction House
     */
    public AHUser(Agent agent, int auctionHouseId,
                  Socket bankSocket, String ip, int port, ObjectOutputStream bankOut,
                  ObjectInputStream bankIn) {
        this.bankIn = bankIn;
        this.bankOut = bankOut;
        this.bankSocket = bankSocket;
        this.agent = agent;
        this.auctionHouseID = auctionHouseId;
        System.out.println("Connecting to Auction House Server");
        Socket sock = null;
        try {
            sock = new Socket(ip, port);
            boolean run = true;
            auctionOut = new ObjectOutputStream(sock.getOutputStream());
            auctionIn = new ObjectInputStream(sock.getInputStream());
            System.out.println("Connection Successful");
            AgentClient client = new AgentClient(agent,sock,auctionOut,auctionIn,bankOut,bankIn);
            new Thread(client).start();
//            listener();

            Scanner sc = new Scanner(System.in);
            while (run) {

                System.out.println("Would you like to make another bid or would yo" +
                        "u like to switch to another auction house? (Bid/Switch");
                String choice = sc.nextLine();
                System.out.println("Choice is: " + choice);
                if(choice.equals("Bid")){
                    System.out.println("I got here");
                    //client.requestItems();
                    client.bid();
                }else if(choice.equals("Switch")){
                    try {
                        //Read in the message
                        agentAuctionChoice(sc);
                        //print the auctions
                        agent.getAvailableHouses().forEach(e-> System.out.print(e.getAuctionID() + "   "));
                        //Get the choice
                        int auctionHouseChoice = validateAgentAuctionHouseChoice(agent,sc);
                        AuctionHouse houseChoice  = agent.getAvailableHouses().get(auctionHouseChoice);
                        //create the socket
                        auctionHouseID = houseChoice.getAuctionID();
                        int choicePort = houseChoice.getPort();
                        String  host = houseChoice.getIp();
                        Socket socket = new Socket(host,choicePort);
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                        //Clone the agent
                        Agent clone = new Agent();
                        clone.setAvailableHouses(agent.getAvailableHouses());
                        clone.setAccountNumber(agent.getAccountNumber());

                        AgentClient agentClient = new AgentClient(clone,sock,out,in,bankOut,bankIn);
                        new Thread(agentClient).start();


                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Closed gracefully");
        }
    }



    private int validateAgentAuctionHouseChoice(Agent agent,Scanner sc) {
        int index;
        ArrayList<Integer> indicies = new ArrayList<>();
        agent.getAvailableHouses().forEach(e -> indicies.add(e.getAuctionID()));
        do {
            System.out.println("Please join an Auction house by typing in their ID below");
            System.out.print("IDs: ");
            agent.getAvailableHouses().forEach(e -> System.out.print(e.getAuctionID() + " "));
            System.out.println();
            while (!sc.hasNextInt()) {
                System.out.println("Enter a valid Auction House number");
                sc.next();
            }
            index = sc.nextInt();
        } while (!indicies.contains(index) );

        for (int i = 0; i < agent.getAvailableHouses().size(); i++) {
            if(agent.getAvailableHouses().get(i).getAuctionID() == index){
                return  i;
            }
        }
        return -1;
    }




    /**
     * Validates whether the agents should await for more
     * Auction houses
     * @param sc
     */
    private void agentAuctionChoice(Scanner sc) {
        String confirm;
        while (true) {
            if (agent.getAvailableHouses().size() == 0) {
                System.out.println("There are currently no Auction Houses available" +
                        ", currently updating list of Auction Houses ");
            } else {
                System.out.println("There are currently " + agent.getAvailableHouses().size()
                        + " Auction Houses available\nDo you want you wait for more Auction Houses to join? (yes/no)");
                confirm = sc.nextLine();
                if (confirm.equals("no")) break;
            }
            try {
                Thread.sleep(1000);
                AgentMessage updateHouses = new AgentMessage(AGENT_UPDATE_AUCTION, agent, "");
                bankOut.writeUnshared(updateHouses);
                BankMessage update = (BankMessage) bankIn.readUnshared();
                agent.setAvailableHouses(update.getHouses());
            } catch (IOException | InterruptedException | ClassNotFoundException sie) {
            }
        }
    }



    private AuctionHouse findHouse(ArrayList<AuctionHouse> houses, int index){
        for(AuctionHouse house: houses){
            if(house.getAuctionID() == index) return house;
        }
        System.err.println("House not found");
        return null;
    }

    /**
     * main starts the program
     * @param args
     */
//    public static void main(String[] args) {
//        new AHTester();
//    }
}
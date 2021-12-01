package AH;

import Agent.Agent;
import Messages.*;
import Agent.Bid;
import java.io.IOException;
import java.io.ObjectInputStream;
import Messages.*;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AgentClient implements Runnable {
    private Socket socket;
    private ObjectInputStream auctionIn;
    private ObjectOutputStream auctionOut;
    private ObjectInputStream bankIn;
    private ObjectOutputStream bankOut;
    private List<Item> items;
    private Agent agent;

    public AgentClient(Agent agent,
                       Socket socket,
                       ObjectOutputStream  auctionOut,
                       ObjectInputStream auctionIn,
                       ObjectOutputStream bankOut,
                       ObjectInputStream bankIn){
        this.socket = socket;
        this.auctionIn = auctionIn;
        this.auctionOut = auctionOut;
        this.bankIn = bankIn;
        this.bankOut = bankOut;
        this.agent = agent;
        setItems();
    }


    @Override
    public void run() {
        while (true) {
            try {
//                    System.out.println("Waiting to read");
                Object m = auctionIn.readUnshared();
                if (m instanceof GetItemMessage) {
                    GetItemMessage bM = (GetItemMessage) m;
                    items = bM.getItems();
                }
                if (m instanceof BidMessage) {
                    BidMessage BM = (BidMessage) m;
                    if (BM.isSuccessful()) {
                        System.out.println("Bid was successful");
                        AgentMessage checkFunds = new AgentMessage(AgentActions.AGENT_CHECK_FUNDS, agent, "");
                        bankOut.writeUnshared(checkFunds);
                        System.out.println(((BankMessage) bankIn.readUnshared()).getReply());
                    } else {
                        System.out.println("Bid was not successful");
                        AgentMessage checkFunds = new AgentMessage(AgentActions.AGENT_CHECK_FUNDS, agent, "");
                        bankOut.writeUnshared(checkFunds);
                        System.out.println(((BankMessage) bankIn.readUnshared()).getReply());
                    }
                } else if (m instanceof OutBidMessage) {
                    OutBidMessage OB = (OutBidMessage) m;
                    System.out.println("Out bid on " + OB.getOutBid().getName());
                } else if (m instanceof ItemWonMessage) {
                    ItemWonMessage IW = (ItemWonMessage) m;
                    System.out.println("Won Item " + IW.getItem().getName() + " for " + "$" + IW.getBid());
                    items.removeIf(e-> e.getItemID() ==IW.getItem().getItemID());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int validateInput(Scanner sc,int size,
                              String prompt, String reply) {
        int index;
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 1; i < size+1; i++) {indices.add(i); }

        do {
            System.out.println(prompt);
            while (!sc.hasNextInt()) {
                System.out.println(reply);
                sc.next();
            }
            index = sc.nextInt();
        } while (!indices.contains(index));

        return index-1;
    }
    private void setItems(){
        try {
            GetItemMessage GI = new GetItemMessage();
            auctionOut.reset();
            auctionOut.writeUnshared(GI);
        }catch(Exception e){e.printStackTrace();}

    }

    public void bid(){
        System.out.println("here boi");
        try {
            //print the stuff
            System.out.print("Name  ");
            items.forEach(e-> System.out.print("    " + e.getName()+"    "));
            System.out.println();
            System.out.print("Min bid:  ");
            items.forEach(e-> System.out.print("$" + e.getMinBid()+"    "));
            System.out.println();

            for (int i = 1; i < items.size()+1; i++) System.out.print("           "+ i + "    ");
            System.out.println();

            Scanner s = new Scanner(System.in);
            int item = validateInput(s,items.size(),"Enter valid " +
                    " item", "Enter valid item") ;
            ArrayList<Double> amounts = new ArrayList<>();
            double minAmount = items.get(item).getMinBid();
            double multiplier = 0;
            int choices = 3;
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            for(int i=0; i < choices; i++){
                amounts.add(minAmount + multiplier);
                System.out.print(formatter.format(minAmount+multiplier)+"      ");
                multiplier+=1000;
            }
            System.out.println();
            for (int i = 1; i < choices+1; i++) {
                System.out.print("       " + i+"        ");
            }
            System.out.println();
            int bidChoice = validateInput(s,amounts.size(),"Enter bid amount" +
                    " out, "," Enter valid  bid choice");
            System.out.println();
            System.out.println("Enter minimum bid amount: ");
            double x = amounts.get(bidChoice);
            BidMessage b = new BidMessage(x, items.get(item), agent.getAccountNumber());
            auctionOut.writeUnshared(b);
            System.out.println("Sending that message");
            setItems();
        }catch (Exception e) {e.printStackTrace();}
    }

}

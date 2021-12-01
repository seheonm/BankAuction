package AH;

import Agent.Agent;
import Messages.AgentActions;
import Messages.AgentMessage;
import Messages.BankMessage;

import java.io.ObjectInputStream;
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
    }


    @Override
    public void run() {

    }

    private int validateInput(Scanner sc, int size,
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
        }catch (Exception e) {e.printStackTrace();}
    }

}

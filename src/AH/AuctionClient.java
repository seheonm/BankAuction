package AH;

import Agent.Bid;
import Messages.BankActions;
import Messages.BankMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AuctionClient implements Runnable {
    private Socket c_sock;
    private ObjectOutputStream agentOut;
    private ObjectInputStream agentIn;
    private ObjectOutputStream bankOut;
    private ObjectInputStream bankIn;
    private AuctionHouse AH;
    private int bankNum;


    public AuctionClient(Socket c_sock,
                         ObjectOutputStream agentOut, ObjectInputStream agentIn,
                         ObjectOutputStream bankOut, ObjectInputStream bankIn,
                         AuctionHouse AH) {
        this.c_sock = c_sock;
        this.agentOut = agentOut;
        this.agentIn = agentIn;
        this.bankOut = bankOut;
        this.bankIn = bankIn;
        this.AH = AH;
    }

    /**
     * outBid Calls to outbid and still needs to send back message of release funds to bank
     *
     * @param item
     */
    public void outBid(Item item) {
        // send back message of release funds
        System.out.println("call to outbid");

    }

    public void winBid(Item item) {
        System.out.println("call to winbid");

    }

    /**
     * runs thread
     */
    @Override
    public void run() {

        System.out.println("Message Received");
    }

}

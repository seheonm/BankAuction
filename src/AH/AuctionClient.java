package AH;

import Agent.Bid;
import Agent.*;
import Messages.*;


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


    /**
     * @param c_sock
     * @param agentOut
     * @param agentIn
     * @param bankOut
     * @param bankIn
     * @param AH
     */
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
        OutBidMessage OB = new OutBidMessage(item);
        try {
            agentOut.writeUnshared(OB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void winBid(Item item) {
        System.out.println("call to winbid");
        ArrayList<Item> itemCopy = new ArrayList<>(AH.getItems());
        ItemWonMessage WM = new ItemWonMessage(item, item.getCurrBid());
        System.out.println(WM.getItem().getCurrBid());
        System.out.println("Before");
        System.out.println(AH.getItems().size());
        for (int i = 0; i < itemCopy.size(); i++) {
            if(itemCopy.get(i).getItemID() == item.getItemID()) {
                AH.getItems().remove(i);
                AH.generateItem();
            }
        }
        System.out.println("After");
        System.out.println(AH.getItems().size());
        try {
            agentOut.writeUnshared(WM);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * runs thread
     */
    @Override
    public void run() {
        while (true) {
            try {

                Object m = agentIn.readUnshared();
                System.out.println("Message Received");
                if (m instanceof BidMessage) {
                    BidMessage bM = (BidMessage) m;
                    bankNum = bM.getAcctNum();
                    boolean checkInAuction = AH.bid(this, bM);
                    //send to Agent
                    System.out.println(bM.getAcctNum());
                    //send to bank
                    if(checkInAuction) {
                        Bid bid = new Bid(bM.getAcctNum(), bM.getItem().getItemID(), bM.getBid());
                        AuctionHouseMessage message = new AuctionHouseMessage(AuctionHouseActions.AUCTION_REVIEW_BID, bid);
                        bankOut.writeUnshared(message);
                        BankMessage reply = (BankMessage) bankIn.readUnshared();
                        System.out.println(reply.getAction());
                        boolean checkInBank = (reply.getAction() == BankActions.BANK_ACCEPT);
                        System.out.println("Check in bank is:" + checkInBank);
                        System.out.println("Check in auction is:" + checkInAuction);
                        BidMessage B = new BidMessage(checkInAuction && checkInBank, bM.getItem());
                        agentOut.writeUnshared(B);
                    }else {
                        BidMessage B = new BidMessage(checkInAuction, bM.getItem());
                        agentOut.writeUnshared(B);
                    }

                } else if (m instanceof GetItemMessage) {
                    System.out.println("In One");
                    List<Item> item = AH.getItems();
                    GetItemMessage GI = new GetItemMessage(item);
                    agentOut.reset();
                    agentOut.writeUnshared(GI);
                    agentOut.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Closed gracefully");
            }
        }
    }

}

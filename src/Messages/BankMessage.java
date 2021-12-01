package Messages;

import AH.AuctionHouse;
import Agent.*;
import Bank.*;
import java.awt.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.List;

public class BankMessage<Bid> extends Message implements Serializable{
    private BankActions action;
    private AuctionHouse house;
    private int accountNumber;
    private Agent agent;
    private List<AuctionHouse> houses;
    private String reply;


    private Bid bid;

    //bid
    public BankMessage(BankActions action, Bid bid, String reply){
        this.action = action;
        this.bid = bid;
        this.reply = reply;
    }
    public BankMessage(BankActions action, int accountNumber,
                       List<AuctionHouse> auctionHouses,
                       String reply){
        this.action= action;
        this.reply = reply;
        this.accountNumber=  accountNumber;
        this.houses = auctionHouses;
    }

    public BankMessage(BankActions action,List<AuctionHouse> auctionHouses){
        this.action = action;
        this.houses = auctionHouses;
    }
    public BankMessage(BankActions action,int accountNumber,String reply){
        this.action= action;
        this.reply = reply;
        this.accountNumber= accountNumber;
    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public BankActions getAction() {
        return action;
    }

    public List<AuctionHouse> getHouses() {return houses; }

    public String getReply() {
        return reply;
    }

    public Agent getAgent() {
        return agent;
    }

    public AuctionHouse getHouse() {
        return house;
    }

    public Bid getBid() {
        return bid;
    }
}


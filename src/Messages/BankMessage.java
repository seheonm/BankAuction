/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

/* Bank Messages */

package Messages;

import AH.AuctionHouse;
import Agent.*;
import java.io.Serializable;
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

    /**
     * Bank Message
     * @param action of type BankActions
     * @param bid of type Bid
     * @param reply of type String
     */
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

    /**
     * Gets Account Number
     * @return accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Gets Action
     * @return action
     */
    public BankActions getAction() {
        return action;
    }

    /**
     * Gets Houses
     * @return houses
     */
    public List<AuctionHouse> getHouses() {
        return houses;
    }

    /**
     * Gets Reply
     * @return reply
     */
    public String getReply() {
        return reply;
    }

    /**
     * Gets agent
     * @return agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Gets House
     * @return house
     */
    public AuctionHouse getHouse() {
        return house;
    }

    /**
     * Gets bid
     * @return bid
     */
    public Bid getBid() {
        return bid;
    }
}


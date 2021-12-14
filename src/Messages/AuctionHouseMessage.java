/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

/* Auction House Messages */

package Messages;

import AH.AuctionHouse;
import Agent.*;

import java.io.Serializable;

public class AuctionHouseMessage extends Message implements Serializable {

    private AuctionHouseActions actions;
    private AuctionHouse house;
    private int auctionHouseID;
    private int agentID;
    private Bid bid;
    private String reply;

    /**
     * Auction House Message
     * @param actions of type AuctionHouseActions
     * @param house of type AuctionHouse
     * @param reply of type String
     */
    public AuctionHouseMessage(AuctionHouseActions actions, AuctionHouse house,String reply) {
        super(actions);
        this.house = house;
        this.reply = reply;
    }


    public AuctionHouseMessage(AuctionHouseActions actions, Bid bid) {
        super(actions);
        this.bid = bid;
    }


    public int getAgentID() { return agentID; }
    public int getAuctionHouseID() {return auctionHouseID; }
    public AuctionHouse getHouse() { return house;    }

    public Bid getBid() {
        return bid;
    }
}


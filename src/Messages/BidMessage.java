/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */
package Messages;

import AH.Item;

import java.io.Serializable;

public class BidMessage implements Serializable {
    private double bid;
    private Item item;
    private int acctNum;
    private boolean successful;

    /**
     * BidMessage constructor creates a bid message
     * with the following parameters
     * @param bid bid
     * @param item item
     * @param acctNum account number
     */
    public BidMessage(double bid, Item item, int acctNum){
        this.acctNum = acctNum;
        this.item = item;
        this.bid = bid;
    }

    /**
     * BidMessage sets successful (true or false) and pass an item
     * @param successful success
     * @param item item
     */
    public BidMessage(boolean successful, Item item){
        this.successful = successful;
        this.item = item;
    }

    /**
     * isSuccessful gets successful
     * @return successful
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * getAcctNum
     * @return acctNum
     */
    public int getAcctNum() {
        return acctNum;
    }

    /**
     * getBid gets bid
     * @return bid
     */
    public double getBid() {
        return bid;
    }

    /**
     * getItem
     * @return item
     */
    public Item getItem(){
        return item;
    }
}

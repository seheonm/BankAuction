/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */
package Messages;

import AH.Item;

import java.io.Serializable;

/**
 * Creates an outbid message
 */
public class OutBidMessage implements Serializable {
    private final Item outBid;

    /**
     * Constructor OutBidMessage
     * @param outBid outbid
     */
    public OutBidMessage(Item outBid){
        this.outBid = outBid;
    }

    /**
     * getOutBid gets the outBid
     * @return outbid
     */
    public Item getOutBid() {
        return outBid;
    }
}

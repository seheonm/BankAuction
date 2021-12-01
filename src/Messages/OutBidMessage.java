package Messages;

import AH.Item;

import java.io.Serializable;

/**
 * Creates an outbid message
 */
public class OutBidMessage implements Serializable {
    private Item outBid;

    /**
     * Constructor OutBidMessage
     * @param outBid
     */
    public OutBidMessage(Item outBid){
        this.outBid = outBid;
    }

    /**
     * getOutBid gets the outBid
     * @return
     */
    public Item getOutBid() {
        return outBid;
    }
}

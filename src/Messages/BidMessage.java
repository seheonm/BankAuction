package Messages;

import AH.Item;

public class BidMessage {
    private double bid;
    private Item item;
    private int acctNum;
    private boolean successful;

    /**
     * BidMessage constructor creates a bid message with the following parameters
     * @param bid
     * @param item
     * @param acctNum
     */
    public BidMessage(double bid, Item item, int acctNum){
        this.acctNum = acctNum;
        this.item = item;
        this.bid = bid;
    }

    /**
     * BidMessage sets successful (true or false) and pass an item
     * @param successful
     * @param item
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

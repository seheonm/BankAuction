/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

/* Item Won Messages */

package Messages;

import AH.Item;

import java.io.Serializable;

public class ItemWonMessage implements Serializable {
    private double bid;
    private Item item;


    public ItemWonMessage(Item item, double bid){
        this.item = item;
        this.bid = bid;
    }

    /**
     * Gets bid
     * @return bid
     */
    public double getBid() {
        return bid;
    }

    /**
     * Gets items
     * @return item
     */
    public Item getItem() {
        return item;
    }
}

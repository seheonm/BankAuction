package Messages;

import AH.Item;

public class ItemWonMessage {
    private double bid;
    private Item item;


    public ItemWonMessage(Item item, double bid){
        this.item = item;
        this.bid = bid;
    }

    public double getBid() {
        return bid;
    }

    public Item getItem() {
        return item;
    }
}

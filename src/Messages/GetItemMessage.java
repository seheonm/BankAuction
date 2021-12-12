/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

/* Get Items Messages */

package Messages;

import AH.Item;

import java.io.Serializable;
import java.util.List;

public class GetItemMessage implements Serializable {
    private List<Item> items;
    public GetItemMessage(){}
    public GetItemMessage(List<Item> items){
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}

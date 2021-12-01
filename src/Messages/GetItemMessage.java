package Messages;

import AH.Item;

import java.util.List;

public class GetItemMessage {
    private List<Item> items;
    public GetItemMessage(){}
    public GetItemMessage(List<Item> items){
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}

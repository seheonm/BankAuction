package Agent;

import java.io.Serializable;

public class Bid  implements Serializable {
    private int agentID;
    private int itemID;
    private double amount;

    public Bid(int agentID, int itemID, double amount){
        this.agentID = agentID;
        this.itemID = itemID;
        this.amount = amount;
    }

    public int getAgentID() {
        return agentID;
    }

    public double getAmount() {
        return amount;
    }

}

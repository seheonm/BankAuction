package Agent;

import AH.AuctionHouse;
import Bank.*;

import java.io.Serializable;
import java.util.List;

/**
 * Getters and setters for the account number and a list of available auction houses for the agent
 */
public class Agent implements Serializable {

    private List<AuctionHouse> availableHouses;
    private int accountNumber;

    /**
     * Sets account number.
     *
     * @param accountNumber the account number
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets account number.
     *
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets available houses.
     *
     * @param availableHouses the available houses
     */
    public void setAvailableHouses(List<AuctionHouse> availableHouses) {
        this.availableHouses = availableHouses;
    }

    /**
     * Gets available houses.
     *
     * @return the available houses
     */
    public List<AuctionHouse> getAvailableHouses() {
        return availableHouses;
    }
}

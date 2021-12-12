/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

package Agent;

import AH.AuctionHouse;
import java.io.Serializable;
import java.util.List;

/**
 * Getters and setters for the account number and a list
 * of available auction houses for the agent
 */
public class Agent implements Serializable {

    private List<AuctionHouse> availableHouses;
    private int accountNumber;

    /**
     * Sets the account number.
     * @param accountNumber of type int
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Gets the account number.
     * @return the account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the available houses.
     * @param availableHouses of type List<AuctionHouse>
     */
    public void setAvailableHouses(List<AuctionHouse> availableHouses) {
        this.availableHouses = availableHouses;
    }

    /**
     * Gets the available houses.
     * @return the available houses
     */
    public List<AuctionHouse> getAvailableHouses() {
        return availableHouses;
    }
}

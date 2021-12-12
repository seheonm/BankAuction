/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

package Bank;

/**
 * Class that has the account number, balance, and name for
 * creating an account for the bank. This account is used for
 * both the auction houses and the agents.
 */
public  class Account{
    private int acctNum;
    private double bal;
    private String name;

    /**
     * Instantiates a new Account.
     * @param acctNum the acct num of type int
     * @param balance the balance of type double
     */
    public Account(int acctNum, double balance){
        this.acctNum = acctNum;
        this.bal =  balance;
        this.name = "";
    }

    /**
     * Sets name.
     * @param name of type String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets acct number
     * @return the account number
     */
    public int getAcctNum() {
        return acctNum;
    }

    /**
     * Gets the balance
     * @return balance
     */
    public double getBal() {
        return bal;
    }

    /**
     * Gets name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Deducts funds when a bid is made.
     * @param amount of type double
     */
    public void deductFunds(double amount){
        this.bal -= amount;
    }


    /**
     * Adds funds to the account for an outbid or win action.
     * @param amount
     */
    public void addFunds(double amount){
        this.bal += amount;
    }
}


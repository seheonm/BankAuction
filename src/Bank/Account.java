/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */
package Bank;



/**
 * Class that has the account number, balance, and name for creating
 * an account for the bank. This account is used for
 * both the auction houses and the agents.
 */
public  class Account{
    private final int acctNum;
    private double bal;
    private String name;

    /**
     * Instantiates a new Account.
     * @param acctNum the acct num
     * @param balance the balance
     */
    public Account(int acctNum, double balance){
        this.acctNum = acctNum;
        this.bal =  balance;
        this.name = "";
    }

    /**
     * Sets name.
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets acct num.
     * @return the acct num
     */
    public int getAcctNum() {
        return acctNum;
    }

    /**
     * Gets bal.
     * @return the bal
     */
    public double getBal() {
        return bal;
    }

    /**
     * Gets name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Deducts funds when a bid is made.
     * @param amount the amount
     */
    public void deductFunds(double amount){
        this.bal -= amount;
    }


    /**
     * Adds funds to the account for an outbid or win action.
     * @param amount the amount
     */
    public void addFunds(double amount){
        this.bal += amount;
    }

}


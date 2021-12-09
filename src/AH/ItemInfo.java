/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */
package AH;

public class ItemInfo {

    private final String name;
    private double price;
    private final int itemID;

    /**
     * Constructor for an Item info
     * @param name name of item
     * @param price item's price
     * @param count item ID
     */
    public ItemInfo(String name, double price, int count) {
        this.name = name;
        this.price = price;
        itemID = count;
    }

    /**
     * Gets the name of the item
     * @return item's name
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the current price
     * @return item's price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get the Item ID
     * @return item's ID
     */
    public int getItemID() {
        return itemID;
    }
    /**
     * Set the price of the ItemInfo
     * @param price price of item
     */
    public void setPrice(double price) { this.price = price; }


}

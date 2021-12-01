package AH;

public class ItemInfo {

    private String name;
    private double price;
    private int itemID;


    public ItemInfo(String name, double price, int count) {
        this.name = name;
        this.price = price;
        itemID = count;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    public int getItemID() {
        return itemID;
    }

    public void setPrice(double price) { this.price = price; }

}

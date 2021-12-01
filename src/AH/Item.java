package AH;

public class Item {

    private String name;
    private double price;
    private String description;
    private int itemID;
    private double minBid;
    private double currBid;
    private int houseId;
    private int itemCount;
    private boolean won;


    public Item(String name, double price, String description, int itemID, int houseId){
        this.name = name;
        this.price = price;
        this.description = description;
        this.itemID = itemID;
        this.houseId = houseId;
        this.currBid = 0;
        this.minBid = price;
        this.itemCount = 0;
        this.won = false;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void increment(){
        itemCount++;
    }

    public void resetTimer(){
        this.itemCount = 0;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    public double getMinBid(){return minBid;}

    public double getCurrBid(){return currBid;}

    public void setMinBid(double minBid) {
        this.minBid = minBid;
    }


    public void setCurrBid(double currBid){
        this.currBid = currBid;
    }

    public int getItemID() {
        return itemID;
    }


    public double getPrice() {
        return price;
    }


}

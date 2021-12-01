package AH;
import Messages.BidMessage;
import java.io.*;
import java.util.*;

public class AuctionHouse implements  Serializable{
    private List<Item> items;
    private List<Item> itemsToSell;
    private int auctionID = 0;
    private String ip;
    private int port;
    private boolean alive;
    private int currItemId;
    private Map<Item, AuctionClient> bidMap;
    private Map<Integer, Item> accountHistory;
    private int winTime = 10;
    private final double BID_OFFSET = 1000;

    //List of states of each agent item




    /**
     * Constructor initializing an Auction House.
     */
    public AuctionHouse(String ip, int port, String file){
        bidMap = new HashMap<>();
        accountHistory = new HashMap<>();
        alive = true;
        this.ip = ip;
        this.port = port;
        this.items  = new ArrayList<>();
        this.itemsToSell = new ArrayList<>();
        this.currItemId = 0;
        loadItems(file);
        /**
         * generating 3 items to test if it works.
         */
        generateItem();
        generateItem();
        generateItem();
        counter();
    }

    private void counter(){
        Thread count = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000);
                    ArrayList<Item> itemListCopy =new ArrayList<>(itemsToSell);
                    for(Item i : itemListCopy){
                        i.increment();
                        if(i.getItemCount() >= winTime && i.getCurrBid() != 0){
                            //Do win bid stuff
                            if(!i.isWon()){
                                bidMap.get(i).winBid(i);
                                i.setWon(true);
                                System.out.println(i.getCurrBid());
                            }


                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        count.start();
    }

    public List<Item> getItems(){
        System.out.println("Got items");
        return itemsToSell;
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public boolean bid(AuctionClient client, BidMessage bid){
        for(Item i: itemsToSell){
            if(i.getItemID() == bid.getItem().getItemID()){
                if(Double.compare( bid.getBid() , i.getMinBid())>= 0){
                    if (accountHistory.containsKey(bid.getAcctNum())) {
                        if(bid.getItem().getItemID() == i.getItemID()){
                            System.out.println("Bid already made");
                            return false;
                        }

                    }
                    if (bidMap.containsKey(i)) {
                        bidMap.get(i).outBid(i);
                    }
                    bidMap.put(i, client);
                    i.setCurrBid(bid.getBid());
                    i.setMinBid(i.getCurrBid() + BID_OFFSET);
                    i.resetTimer();
                    System.out.println("Successful bid on " + i.getName() + " for " + bid.getBid());
                    System.out.println(i.getCurrBid());
                    return true;
                }
            }
        }
        System.out.println("Unsuccessful bid on " + bid.getItem().getName());
        return false;
    }
    /**
     * loadItems reads in the items from items.txt file.
     */
    private void loadItems(String file){
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(new File(file)));
            String line;

            // Read in the items
            while ((line = bReader.readLine()) != null) {
                String[] x = line.split(",");
                Item item = new Item(x[0], Integer.parseInt(x[1]), x[2], 0, 0);
                items.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeItems(int currItemId){
        items.removeIf(e-> e.getItemID()== currItemId);
    }

    /**
     * @return If the auction house is alive or not;
     */
    public boolean isAlive(){
        return alive;
    }

    /**
     * Picking random items from the items to sell list.
     */
    public void generateItem(){
        Random ran = new Random();
        Item i = items.get(ran.nextInt(items.size()));
        itemsToSell.add(new Item(i.getName(), i.getMinBid(), i.getDescription(), currItemId++, auctionID));
    }

    /**
     * printItems prints the items.
     */
    public void printItems(){
        for(Item i: itemsToSell){
            System.out.println(i.getName() + ", " + i.getPrice() + ", " + i.getDescription());
        }
    }
}

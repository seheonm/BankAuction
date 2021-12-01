package AH;

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

    public boolean bid(){
        return true;
    }

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

    public boolean isAlive(){
        return alive;
    }

    public void generateItem(){
        Random ran = new Random();
        Item i = items.get(ran.nextInt(items.size()));
        itemsToSell.add(new Item(i.getName(), i.getMinBid(), i.getDescription(), currItemId++, auctionID));
    }

    public void printItems(){
        for(Item i: itemsToSell){
            System.out.println(i.getName() + ", " + i.getPrice() + ", " + i.getDescription());
        }
    }
}

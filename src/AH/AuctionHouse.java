/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

package AH;
import Messages.BidMessage;
import java.io.*;
import java.util.*;

public class AuctionHouse implements  Serializable{
    private final List<Item> items;
    private final List<Item> itemsToSell;
    private int auctionID = 0;
    private final String ip;
    private final int port;
    private final boolean alive;
    private int currItemId;
    private final Map<Item, AuctionClient> bidMap;
    private final Map<Integer, Item> accountHistory;
    private final int winTime = 10;
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

        //generating 3 items to test if it works.

        generateItem();
        generateItem();
        generateItem();
        counter();
    }

    /**
     * Counter
     */
    private void counter(){
        Thread count = new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000);
                    ArrayList<Item> itemListCopy=new ArrayList<>(itemsToSell);
                    for(Item i : itemListCopy){
                        i.increment();
                        if(i.getItemCount() >= winTime &&
                                i.getCurrBid() != 0){
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

    /**
     * Get items to be sold
     * @return Items to be sold
     */
    public List<Item> getItems(){
        System.out.println("Got items");
        return itemsToSell;
    }

    /**
     * Get port number
     * @return port number
     */
    public int getPort() {
        return port;
    }

    /**
     * Get IP number
     * @return IP address
     */
    public String getIp() {
        return ip;
    }

    /**
     * Set auction ID
     * @param auctionID action house ID of type int
     */
    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    /**
     * Get auction ID
     * @return auction ID
     */
    public int getAuctionID() {
        return auctionID;
    }

    /**
     * Bid
     * @param client client
     * @param bid bid message
     * @return true if bid successfully
     */
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
                    System.out.println("Successful bid on " +
                            i.getName() + " for " + bid.getBid());
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
     * @param file file of items of type String
     */
    private void loadItems(String file){
//        try {
//            InputStream fileStream = getClass().getResourceAsStream(file);
//            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String[] input = new String[]{
"Bugatti,1000000,2005 Bugatti Veyron Four turbos on an 8 liter W16",
"Lamborghini,250000,1967 Lamborghini Minura LP400 3.9 liter V12",
"Ferrari,250000,1969 Ferrari 365 GTB/4 Daytona 352-hp V12",
"McLaren,200000,1992 McLaren F1 6.1-liter 618-hp V12 Top speed is 231-mph",
"Lamborghini,150000,1974 Lamborghini Countach 5.2-liter V12 rated at 455-hp",
"Benz,500000,1954 Mercades-Benz 300SL direct-injection six Rating: Stupid fast",
"Ferrari,300000,1987 Ferrari F40 Twin turbo 471-hp V8 rating: Exhilarating!",
"Porsche,80000,1986 Porsche 959 twin turbo flat-6",
"GT40,1000000,1964 Ford GT40 Four-time Le Mans winner",
"Ferrari,670000,2002 Ferrari Enzo 6.0-liter V12 651-hp",
"Duesenberg,1400000,1932 Duesenberg SJ huge supercharger need I say more",
"McLaren,200000,2014 McLaren P1 3.8-liter twin-turbo V8",
"Porsche,845000,2014 Porsche 918 Spyder 4.6-liter V8",
"ShelbyCobra,1000000,1966 Shelby Cobra 427 straight-6 engine 425-hp",
"Venom,1000000,Hennessey Venom GT 6-speed twin-turbo Rating: Stupid fast!",
"Acura,157000,1990 Acura NSX 270-hp V6",
"stutz,345000,1912 Stutz Bearcat 5.8-liter 16-valve Four",
"RollsRoyce,450000,1908 Rolls-Royce 40/50 Silver Ghost 6.8-liter V12",
"Porsche,110000,1976 Porsche 911 Turbo 3.0-liter turbo engine",
"GT,500000,2005 Ford GT Supercharged 5.4-liter V8 rated at 550 hp",
"M1,60000,1979 BMW M1 3.5-liter M88 inline-6 making 273 hp",
"Viper,145000,1992 Dodge SRT Viper 640 hp from its 8.4-liter V10",
"Lexus,375000,2011 Lexus LFA 4.8-liter V10 that screams out 552 hp",
"Corvette,120000,2009 Chevrolet Corvette ZR1 A supercharged 6.2-liter V8",
"GTR,115000,2007 Nissan GT-R (R35) 3.8-liter twin-turbo V6 started at 485 hp",
"AstonMartin,250000,2010 Aston Martin One-77 750 hp under a taut skin",
"XKSS,1000000,1957 Jaguar XKSS inline-6",
"Bentley,300000,1927 Bentley 4-1/2 Litre Supercharged 4.5-liter fours making 130 hp",
"2000GT,1000000,1967 Toyota 2000GT 2.0-liter 150-hp six from Yamaha",
"Skyline,145000,1999 Nissan Skyline GT-R (R34) all wheel drive runs 0-100 in 4.4 seconds"};

//            String line;

            // Read in the items
//            while ((line = bReader.readLine()) != null) {
            for (String line : input) {
                String[] x = line.split(",");
                Item item = new Item(x[0],
                        Integer.parseInt(x[1]), x[2], 0, 0);
                items.add(item);
            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Picking random items from the items to sell list.
     */
    public void generateItem(){
        Random ran = new Random();
        Item i = items.get(ran.nextInt(items.size()));
        itemsToSell.add(new Item(i.getName(), i.getMinBid(),
                i.getDescription(), currItemId++, auctionID));
    }
}

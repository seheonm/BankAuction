CS351L Project 5 - Bank Auction by Marina Seheon, Ruby Ta, and Joseph Barela.

Joseph worked on the Auction House portions.
Ruby worked on the Agent portions.
Marina worked on the Bank portions.
We all met frequently to help each other with code and to run and test.

## Functionality:
* Our program can handle one Bank and multiple Auction Houses and Clients running simultaneously.
* The Bank program is the first program to be run, following by any Auction House programs, and Clients.
* A bid is successful if not overtaken in 30 second.
* We reverted the program into Java 8 in order for it to work on the CS Machines

### BANK:
* The Bank is a server and the Clients and Auction Houses are its clients.
* Both agents and auction houses will have bank accounts.
* When an agent wins an auction, the bank will transfer these blocked funds from the agent to the auction house account.
* Auction houses provide the bank with their host and port information. The bank provides the agents with the list of the auction houses and their addresses so the agents will be able to connect directly to the auction houses.

### AUCTION HOUSE:
* Upon creation, it registers with the bank, opening an account with zero balance. It also provides the bank with its host and port address.
* Initially, the auction house will offer at least 3 items for sale.
* Upon request, it shares the list of items being auctioned and the bidding status with agents, including for each item house id, item id, description, minimum bid and current bid.

### AGENT:
* Upon creation, it opens a bank account by providing a name and an initial balance, and receives a unique account number.
* When an agent makes a bid on an item, it receives back one or more status messages.

Bugs:
No bugs.

Extra Credit:
Clean and organized.

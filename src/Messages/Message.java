/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

/* Messages */

package Messages;

import java.io.Serializable;

public abstract  class Message implements Serializable {
    private final Enum action;
    public Message(Enum action){
        this.action = action;
    }
    public Message(){
        this.action = null;
    }


    public Enum getMessageAction() {
        return action;
    }

}

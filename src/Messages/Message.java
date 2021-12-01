package Messages;

import Agent.Agent;

import java.awt.*;
import java.io.Serializable;

public abstract  class Message implements Serializable {
    private Enum action;
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

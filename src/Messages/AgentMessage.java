package Messages;

import Agent.Agent;
import Agent.*;

import java.io.Serializable;

public class AgentMessage extends Message implements Serializable {
    private AgentActions action;
    private Agent agent;
    private String reply;
    private Bid bid;

    public AgentMessage(AgentActions action,Agent agent,String reply){
        super(action);
        this.agent = agent;
        this.reply = reply;
    }

    public AgentMessage(AgentActions action){
        super(action);
    }

    public AgentMessage(AgentActions action, Agent agent){
        super(action);
        this.agent = agent;
    }

    public AgentMessage(AgentActions action, Agent agent, Bid bid){
        super(action);
        this.agent = agent;
        this.bid = bid;
    }

    public String getReply() {
        return reply;
    }

    public Agent getAgent() {
        return agent;
    }

    public Bid getBid() {
        return bid;
    }
}

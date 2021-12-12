/**
 * CS351L Project 5: Auction House
 * by: Ruby Ta, Marina Seheon, Joseph Barela
 */

/* Agent Message */

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

    /**
     * The agent message
     * @param action of type AgentActions
     */
    public AgentMessage(AgentActions action){
        super(action);
    }

    /**
     * Gets the reply
     * @return reply
     */
    public String getReply() {
        return reply;
    }

    /**
     * Gets the agent
     * @return agent
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Gets the bid
     * @return bid
     */
    public Bid getBid() {
        return bid;
    }
}

package edu.fudan.se.agent;

import edu.fudan.se.crowdservice.wrapper.ConversationType;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Dawnwords on 2015/3/4.
 */
public class ACLUtil {
    public static void sendMessage(Agent agent, ConversationType conversationType, String receiverGUID, Serializable content) {
        sendMessage(agent, conversationType, new AID(receiverGUID, false), content);
    }

    public static void sendMessage(Agent agent, ConversationType conversationType, AID receiver, Serializable content) {
        ACLMessage aclMsg = new ACLMessage(ACLMessage.INFORM);
        aclMsg.setConversationId(conversationType.name());
        aclMsg.addReceiver(receiver);
        try {
            aclMsg.setContentObject(content);
            System.out.printf("%s to %s: %s\n", conversationType.name(), aclMsg.getSender(), content.toString());
            agent.send(aclMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

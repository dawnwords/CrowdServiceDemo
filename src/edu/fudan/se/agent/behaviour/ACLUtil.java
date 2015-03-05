package edu.fudan.se.agent.behaviour;

import edu.fudan.se.crowdservice.wrapper.ConversationType;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dawnwords on 2015/3/4.
 */
public class ACLUtil {
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm:ss");

    public static void sendMessage(Agent agent, ConversationType conversationType, String receiverGUID, Serializable content) {
        sendMessage(agent, conversationType, new AID(receiverGUID, false), content);
    }

    public static void sendMessage(Agent agent, ConversationType conversationType, AID receiver, Serializable content) {
        ACLMessage aclMsg = new ACLMessage(ACLMessage.INFORM);
        aclMsg.setConversationId(conversationType.name());
        aclMsg.addReceiver(receiver);
        try {
            aclMsg.setContentObject(content);
            System.out.printf("[%s]%s to %s: %s\n", timeString(), conversationType.name(), receiver, content.toString());
            agent.send(aclMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String timeString() {
        return TIME_FORMAT.format(new Date());
    }
}

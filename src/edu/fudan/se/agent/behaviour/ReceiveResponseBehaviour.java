package edu.fudan.se.agent.behaviour;

import edu.fudan.se.crowdservice.wrapper.*;
import edu.fudan.se.dbopration.UpdateResponseOperator;
import edu.fudan.se.util.XMLUtil;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class ReceiveResponseBehaviour extends
        MessageReceivingBehaviour<ResponseWrapper> {

    public ReceiveResponseBehaviour() {
        super(ConversationType.RESPONSE);
    }

    @Override
    protected void handleMessage(AID sender, ResponseWrapper content) {
        Boolean success = new UpdateResponseOperator(sender.getLocalName(), content.taskId,
                XMLUtil.obj2XML(content.keyValueHolders)).getResult();
        if (success != null && success) {
            sendMessage(sender, ConversationType.COMPLETE.name(), new CompleteWrapper(content.taskId));
        } else {
            sendMessage(sender, ConversationType.REFUSE.name(), new RefuseWrapper(content.taskId, RefuseWrapper.Reason.OFFER_OUT_OF_DATE));
        }
    }

    private void sendMessage(AID sender, String name, Wrapper wrapper) {
        ACLMessage aclMsg = new ACLMessage(ACLMessage.INFORM);
        aclMsg.setConversationId(name);
        aclMsg.addReceiver(sender);
        try {
            aclMsg.setContentObject(wrapper);
            myAgent.send(aclMsg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

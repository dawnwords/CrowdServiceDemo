package edu.fudan.se.agent.behaviour;

import edu.fudan.se.crowdservice.wrapper.ConversationType;
import edu.fudan.se.crowdservice.wrapper.OfferWrapper;
import edu.fudan.se.crowdservice.wrapper.RefuseWrapper;
import edu.fudan.se.dbopration.InsertOfferOperator;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

public class ReceiveOfferBehaviour extends MessageReceivingBehaviour<OfferWrapper> {

    public ReceiveOfferBehaviour() {
        super(ConversationType.OFFER);
    }

    @Override
    protected void handleMessage(AID sender, OfferWrapper content) {
        Boolean offerSuccess = new InsertOfferOperator(sender.getLocalName(), content.taskId, content.price).getResult();
        if (offerSuccess == null || !offerSuccess) {
            ACLMessage aclMsg = new ACLMessage(ACLMessage.INFORM);
            aclMsg.setConversationId(ConversationType.REFUSE.name());
            aclMsg.addReceiver(sender);
            try {
                aclMsg.setContentObject(new RefuseWrapper(content.taskId, RefuseWrapper.Reason.OFFER_OUT_OF_DATE));
                myAgent.send(aclMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
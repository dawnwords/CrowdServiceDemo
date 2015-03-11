package edu.fudan.se.agent.behaviour;

import edu.fudan.se.crowdservice.wrapper.ConversationType;
import edu.fudan.se.crowdservice.wrapper.OfferWrapper;
import edu.fudan.se.crowdservice.wrapper.RefuseWrapper;
import edu.fudan.se.dbopration.InsertOfferOperator;
import jade.core.AID;

public class ReceiveOfferBehaviour extends MessageReceivingBehaviour<OfferWrapper> {

    public ReceiveOfferBehaviour() {
        super(ConversationType.OFFER);
    }

    @Override
    protected void handleMessage(AID sender, OfferWrapper offer) {
        Boolean offerSuccess = new InsertOfferOperator(sender.getLocalName(), offer).getResult();
        if (offerSuccess == null || !offerSuccess) {
            RefuseWrapper content = new RefuseWrapper(offer.taskId, RefuseWrapper.Reason.OFFER_OUT_OF_DATE);
            ACLUtil.sendMessage(myAgent, ConversationType.REFUSE, sender, content);
        }
    }
}
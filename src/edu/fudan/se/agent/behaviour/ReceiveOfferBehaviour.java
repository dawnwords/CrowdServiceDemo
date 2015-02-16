package edu.fudan.se.agent.behaviour;

import jade.core.AID;
import edu.fudan.se.agent.ConversationType;
import edu.fudan.se.crowdservice.wrapper.OfferWrapper;
import edu.fudan.se.dbopration.InsertOfferOperator;

public class ReceiveOfferBehaviour extends MessageReceivingBehaviour<OfferWrapper> {

	public ReceiveOfferBehaviour() {
		super(ConversationType.OFFER);
	}
	
	@Override
	protected void handleMessage(AID sender, OfferWrapper content) {
		new InsertOfferOperator(sender.getLocalName(), content.taskId, content.price).getResult();
	}
}
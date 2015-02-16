package edu.fudan.se.agent.behaviour;

import jade.core.AID;
import edu.fudan.se.agent.ConversationType;
import edu.fudan.se.dbopration.InsertCapacityOperator;

public class ReceiveCapacityBehaviour extends MessageReceivingBehaviour<String> {

	public ReceiveCapacityBehaviour() {
		super(ConversationType.CAPACITY);
	}

	@Override
	protected void handleMessage(AID sender, String capacity) {
		new InsertCapacityOperator(sender.getLocalName(), capacity).getResult();
	}

}

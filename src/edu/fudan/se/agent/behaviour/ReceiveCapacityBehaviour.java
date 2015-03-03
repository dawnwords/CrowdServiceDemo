package edu.fudan.se.agent.behaviour;

import edu.fudan.se.crowdservice.wrapper.ConversationType;
import edu.fudan.se.dbopration.InsertCapacityOperator;
import jade.core.AID;

public class ReceiveCapacityBehaviour extends MessageReceivingBehaviour<String> {

    public ReceiveCapacityBehaviour() {
        super(ConversationType.CAPACITY);
    }

    @Override
    protected void handleMessage(AID sender, String capacity) {
        new InsertCapacityOperator(sender.getLocalName(), capacity).getResult();
    }

}

package edu.fudan.se.agent.behaviour;

import edu.fudan.se.crowdservice.wrapper.ConversationType;
import edu.fudan.se.dbopration.UpdateHeartbeatOperator;
import jade.core.AID;

public class ReceiveHeartbeatBehaviour extends
        MessageReceivingBehaviour<String> {

    public ReceiveHeartbeatBehaviour() {
        super(ConversationType.HEARTBEAT);
    }

    @Override
    protected void handleMessage(AID sender, String content) {
        String[] tokens = content.split(":");
        double longitude = Double.parseDouble(tokens[0]);
        double latitude = Double.parseDouble(tokens[1]);
        new UpdateHeartbeatOperator(longitude, latitude, sender.getLocalName())
                .getResult();
    }

}

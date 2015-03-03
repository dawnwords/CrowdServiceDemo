package edu.fudan.se.agent;

import edu.fudan.se.agent.behaviour.ReceiveCapacityBehaviour;
import edu.fudan.se.agent.behaviour.ReceiveHeartbeatBehaviour;
import edu.fudan.se.agent.behaviour.UpdateOnlineBehaviour;
import edu.fudan.se.crowdservice.wrapper.ConversationType;


public class CheckinAgent extends ServerAgent {

	@Override
	protected void setup() {
		super.setup();
		addBehaviour(new ReceiveCapacityBehaviour());
		addBehaviour(new ReceiveHeartbeatBehaviour());
		addBehaviour(new UpdateOnlineBehaviour(this));
	}

	@Override
	protected String getAgentName() {
		return ConversationType.CHECKIN_AGENT;
	}
}

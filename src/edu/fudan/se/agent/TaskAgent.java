package edu.fudan.se.agent;

import edu.fudan.se.agent.behaviour.BroadcastDelegateBehaviour;
import edu.fudan.se.agent.behaviour.BroadcastRequestBehaviour;
import edu.fudan.se.agent.behaviour.ReceiveOfferBehaviour;
import edu.fudan.se.agent.behaviour.ReceiveResponseBehaviour;


public class TaskAgent extends ServerAgent {
	@Override
	protected void setup() {
		super.setup();
		addBehaviour(new ReceiveResponseBehaviour());
		addBehaviour(new ReceiveOfferBehaviour());
		addBehaviour(new BroadcastDelegateBehaviour(this));
		addBehaviour(new BroadcastRequestBehaviour(this));
	}

	@Override
	protected String getAgentName() {
		return ConversationType.TASK_AGENT;
	}
}

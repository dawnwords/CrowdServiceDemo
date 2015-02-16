package edu.fudan.se.agent.behaviour;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import edu.fudan.se.dbopration.UpdateOnlineOperator;

public class UpdateOnlineBehaviour extends TickerBehaviour {

	private static final int TIMEOUT_SECOND = 10;
	
	public UpdateOnlineBehaviour(Agent a) {
		super(a, TIMEOUT_SECOND * 1000);
	}

	@Override
	protected void onTick() {
		new UpdateOnlineOperator(TIMEOUT_SECOND).getResult();
	}

}

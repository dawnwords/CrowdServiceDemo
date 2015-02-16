package edu.fudan.se.agent;

import java.util.Date;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public abstract class ServerAgent extends Agent {

	private ThreadedBehaviourFactory tbf;
	
	protected void setup() {
		super.setup();
		tbf = new ThreadedBehaviourFactory();
		register();
	}

	private void register() {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName(getLocalName() + new Date().getTime());
		sd.setType(getAgentName());
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	protected abstract String getAgentName();
	
	@Override
	public final void addBehaviour(Behaviour b) {
		super.addBehaviour(tbf.wrap(b));
	}
}

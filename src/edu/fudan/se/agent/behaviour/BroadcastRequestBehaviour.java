package edu.fudan.se.agent.behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.List;

import edu.fudan.se.agent.ConversationType;
import edu.fudan.se.bean.MicroTask;
import edu.fudan.se.crowdservice.wrapper.RequestWrapper;
import edu.fudan.se.dbopration.SelectOnlineAgentIDOperator;
import edu.fudan.se.dbopration.UpdateMicroTaskInitial2OfferOperator;

public class BroadcastRequestBehaviour extends TickerBehaviour {

	private static final long SELECT_INITAL_MICROTASK_PERIOD = 1000;

	public BroadcastRequestBehaviour(Agent a) {
		super(a, SELECT_INITAL_MICROTASK_PERIOD);
	}

	@Override
	protected void onTick() {
		List<MicroTask> initalTasks = new UpdateMicroTaskInitial2OfferOperator()
				.getResult();
		if (initalTasks.size() == 0) {
			return;
		}
		List<AID> onlineAgentIds = new SelectOnlineAgentIDOperator()
				.getResult();
		for (MicroTask initialTask : initalTasks) {
			for (AID onlineAgentId : onlineAgentIds) {
				if(onlineAgentId.getLocalName().equals(initialTask.consumer))
					continue;
				ACLMessage aclMsg = new ACLMessage(ACLMessage.INFORM);
				aclMsg.setConversationId(ConversationType.REQUEST.name());
				aclMsg.addReceiver(onlineAgentId);
				try {
					String description = getDescriptionFromTemplate(initialTask.template);
					aclMsg.setContentObject(new RequestWrapper(initialTask.id,
							description));
					myAgent.send(aclMsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String getDescriptionFromTemplate(String template) {
		int start = template.indexOf("<Description>")
				+ "<Description>".length();
		int end = template.indexOf("</Description>");
		return template.substring(start, end);
	}
}

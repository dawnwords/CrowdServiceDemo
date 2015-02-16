package edu.fudan.se.agent.behaviour;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.fudan.se.agent.ConversationType;
import edu.fudan.se.bean.AgentOffer;
import edu.fudan.se.bean.MicroTask;
import edu.fudan.se.crowdservice.kv.KeyValueHolder;
import edu.fudan.se.crowdservice.wrapper.DelegateWrapper;
import edu.fudan.se.dbopration.SelectOfferByMicroTaskOperator;
import edu.fudan.se.dbopration.UpdateMicroTaskOffer2ProcessingOperator;
import edu.fudan.se.dbopration.UpdateOfferSelectOperator;
import edu.fudan.se.tfws.CrowdServicePlanner;
import edu.fudan.se.util.XMLUtil;

public class BroadcastDelegateBehaviour extends TickerBehaviour {

	public static final long OFFER_SELECT_PEROID = 1000;
	private Logger logger = Logger.getJADELogger(getClass().getName());
	
	public BroadcastDelegateBehaviour(Agent a) {
		super(a, OFFER_SELECT_PEROID);
	}

	@Override
	protected void onTick() {
		List<MicroTask> offerTasks = new UpdateMicroTaskOffer2ProcessingOperator()
				.getResult();
		for (final MicroTask offerTask : offerTasks) {
			new TaskBroadCastThread(offerTask).start();
		}
	}

	class TaskBroadCastThread extends Thread {
		private MicroTask offerTask;

		public TaskBroadCastThread(MicroTask offerTask) {
			this.offerTask = offerTask;
		}
		@Override
		public void run() {
			List<AgentOffer> offerAgents = new SelectOfferByMicroTaskOperator(
					offerTask).getResult();
			List<AgentOffer> selectedAgent = CrowdServicePlanner
					.getSelectedAgent(offerTask.compositeService,
							offerTask.crowdService, offerTask.cost,
							offerTask.deadline, offerAgents);
			new UpdateOfferSelectOperator(selectedAgent).getResult();
			for (AgentOffer selectAgent : selectedAgent) {
				ACLMessage aclMsg = new ACLMessage(ACLMessage.INFORM);
				aclMsg.setConversationId(ConversationType.DELEGATE.name());
				aclMsg.addReceiver(new AID(selectAgent.guid, false));
				try {
					ArrayList<KeyValueHolder> xml2Obj = XMLUtil
							.xml2Obj(offerTask.template);
					logger.info(Arrays.toString(xml2Obj.toArray()));
					aclMsg.setContentObject(new DelegateWrapper(offerTask.id,
							xml2Obj));
					myAgent.send(aclMsg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

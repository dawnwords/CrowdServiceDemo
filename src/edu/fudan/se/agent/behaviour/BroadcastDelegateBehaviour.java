package edu.fudan.se.agent.behaviour;

import edu.fudan.se.agent.ACLUtil;
import edu.fudan.se.bean.AgentOffer;
import edu.fudan.se.bean.MicroTask;
import edu.fudan.se.crowdservice.wrapper.ConversationType;
import edu.fudan.se.crowdservice.wrapper.DelegateWrapper;
import edu.fudan.se.crowdservice.wrapper.RefuseWrapper;
import edu.fudan.se.dbopration.SelectOfferByMicroTaskOperator;
import edu.fudan.se.dbopration.UpdateMicroTaskOffer2ProcessingOperator;
import edu.fudan.se.dbopration.UpdateOfferSelectOperator;
import edu.fudan.se.tfws.CrowdServicePlanner;
import edu.fudan.se.util.XMLUtil;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.List;

public class BroadcastDelegateBehaviour extends TickerBehaviour {

    public static final long OFFER_SELECT_PERIOD = 1000;

    public BroadcastDelegateBehaviour(Agent a) {
        super(a, OFFER_SELECT_PERIOD);
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
            informDelegate(selectedAgent);
            informRefuse(offerAgents, selectedAgent);
        }

        private void informRefuse(List<AgentOffer> offerAgents, List<AgentOffer> selectedAgents) {
            for (AgentOffer offerAgent : offerAgents) {
                for (AgentOffer selectedAgent : selectedAgents) {
                    if (selectedAgent.guid.equals(offerAgent.guid)) {
                        continue;
                    }
                }
                ACLMessage aclMsg = new ACLMessage(ACLMessage.INFORM);
                aclMsg.setConversationId(ConversationType.DELEGATE.name());
                aclMsg.addReceiver(new AID(offerAgent.guid, false));
                try {
                    aclMsg.setContentObject(new RefuseWrapper(offerTask.id, RefuseWrapper.Reason.OFFER_NOT_SELECTED));
                    myAgent.send(aclMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void informDelegate(List<AgentOffer> selectedAgent) {
            for (AgentOffer selectAgent : selectedAgent) {
                DelegateWrapper content = new DelegateWrapper(offerTask.id, XMLUtil.xml2Obj(offerTask.template));
                ACLUtil.sendMessage(myAgent, ConversationType.DELEGATE, selectAgent.guid, content);
            }
        }
    }

}

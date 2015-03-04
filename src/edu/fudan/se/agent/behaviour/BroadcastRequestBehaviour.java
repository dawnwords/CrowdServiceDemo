package edu.fudan.se.agent.behaviour;

import edu.fudan.se.agent.ACLUtil;
import edu.fudan.se.bean.MicroTask;
import edu.fudan.se.crowdservice.wrapper.ConversationType;
import edu.fudan.se.crowdservice.wrapper.RequestWrapper;
import edu.fudan.se.dbopration.SelectOnlineAgentIDOperator;
import edu.fudan.se.dbopration.UpdateMicroTaskInitial2OfferOperator;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.util.List;

public class BroadcastRequestBehaviour extends TickerBehaviour {

    private static final long SELECT_INITAL_MICROTASK_PERIOD = 1000;

    public BroadcastRequestBehaviour(Agent a) {
        super(a, SELECT_INITAL_MICROTASK_PERIOD);
    }

    private static String getDescriptionFromTemplate(String template) {
        int start = template.indexOf("<Description>")
                + "<Description>".length();
        int end = template.indexOf("</Description>");
        return template.substring(start, end);
    }

    @Override
    protected void onTick() {
        List<MicroTask> initialTasks = new UpdateMicroTaskInitial2OfferOperator()
                .getResult();
        if (initialTasks.size() == 0) {
            return;
        }
        List<AID> onlineAgentIds = new SelectOnlineAgentIDOperator()
                .getResult();
        for (MicroTask initialTask : initialTasks) {
            for (AID onlineAgentId : onlineAgentIds) {
                if (onlineAgentId.getLocalName().equals(initialTask.consumer))
                    continue;
                RequestWrapper content = new RequestWrapper(initialTask.id, getDescriptionFromTemplate(initialTask.template));
                ACLUtil.sendMessage(myAgent, ConversationType.REQUEST, onlineAgentId, content);
            }
        }
    }
}

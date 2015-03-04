package edu.fudan.se.agent.behaviour;

import edu.fudan.se.agent.ACLUtil;
import edu.fudan.se.crowdservice.wrapper.*;
import edu.fudan.se.dbopration.UpdateResponseOperator;
import edu.fudan.se.util.XMLUtil;
import jade.core.AID;

public class ReceiveResponseBehaviour extends
        MessageReceivingBehaviour<ResponseWrapper> {

    public ReceiveResponseBehaviour() {
        super(ConversationType.RESPONSE);
    }

    @Override
    protected void handleMessage(AID sender, ResponseWrapper response) {
        Boolean success = new UpdateResponseOperator(sender.getLocalName(), response.taskId,
                XMLUtil.obj2XML(response.keyValueHolders)).getResult();
        ConversationType ct;
        Wrapper content;
        if (success != null && success) {
            ct = ConversationType.COMPLETE;
            content = new CompleteWrapper(response.taskId);
        } else {
            ct = ConversationType.REFUSE;
            content = new RefuseWrapper(response.taskId, RefuseWrapper.Reason.OFFER_OUT_OF_DATE);
        }
        ACLUtil.sendMessage(myAgent, ct, sender, content);
    }
}

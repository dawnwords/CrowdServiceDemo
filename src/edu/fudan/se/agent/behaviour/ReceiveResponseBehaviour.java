package edu.fudan.se.agent.behaviour;

import jade.core.AID;
import edu.fudan.se.agent.ConversationType;
import edu.fudan.se.crowdservice.wrapper.ResponseWrapper;
import edu.fudan.se.dbopration.InsertResponseOperator;
import edu.fudan.se.util.XMLUtil;

public class ReceiveResponseBehaviour extends
		MessageReceivingBehaviour<ResponseWrapper> {

	public ReceiveResponseBehaviour() {
		super(ConversationType.RESPONSE);
	}

	@Override
	protected void handleMessage(AID sender, ResponseWrapper content) {
		new InsertResponseOperator(sender.getLocalName(), content.taskId,
				XMLUtil.obj2XML(content.keyValueHolders)).getResult();
	}
}

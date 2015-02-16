package edu.fudan.se.agent.behaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import java.io.Serializable;

import edu.fudan.se.agent.ConversationType;

/**
 * Created by Jiahuan on 2015/1/22.
 */
public abstract class MessageReceivingBehaviour<T extends Serializable> extends
		CyclicBehaviour {

	private ConversationType conversationType;

	public MessageReceivingBehaviour(ConversationType conversationType) {
		this.conversationType = conversationType;
	}

	@Override
	public void action() {
		MessageTemplate mt = MessageTemplate.and(
				MessageTemplate.MatchPerformative(ACLMessage.INFORM),
				MessageTemplate.MatchConversationId(conversationType.name()));
		ACLMessage aclMsg = myAgent.receive(mt);
		if (aclMsg != null) {
			try {
				handleMessage(aclMsg.getSender(), (T) aclMsg.getContentObject());
			} catch (UnreadableException e) {
				e.printStackTrace();
			}
		} else {
			block();
		}
	}

	protected abstract void handleMessage(AID sender, T content);

}
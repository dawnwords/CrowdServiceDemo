package edu.fudan.se.agent;


import edu.fudan.se.crowdservice.wrapper.ConversationType;

public class Main {

	public static void main(String[] args) {
		args = new String[] {
				"-gui",
				String.format("%s:%s;%s:%s", ConversationType.CHECKIN_AGENT,
						CheckinAgent.class.getName(),
						ConversationType.TASK_AGENT, TaskAgent.class.getName()) };
		jade.Boot.main(args);
	}
}

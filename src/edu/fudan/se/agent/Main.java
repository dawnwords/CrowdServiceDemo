package edu.fudan.se.agent;


import edu.fudan.se.crowdservice.wrapper.ConversationType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {

	static {
		try {
			System.setErr(new PrintStream(new FileOutputStream(new File("error")),true));
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	public static void main(String[] args) {

		args = new String[] {
				"-gui",
				String.format("%s:%s;%s:%s", ConversationType.CHECKIN_AGENT,
						CheckinAgent.class.getName(),
						ConversationType.TASK_AGENT, TaskAgent.class.getName()) };
		jade.Boot.main(args);
	}
}

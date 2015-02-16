package edu.fudan.se.bean;

public class MicroTask {
	
	public static enum State{
		INITIAL,OFFER,PROCESSING,FINISHED
	}

	public final long id;
	public final String template;
	public final String consumer;
	public final int cost;
	public final int deadline;
	public final String compositeService;
	public final String crowdService;
	
	public MicroTask(long id, String template, String consumer, int cost,
			int deadline, String compositeService, String crowdService) {
		this.id = id;
		this.template = template;
		this.consumer = consumer;
		this.cost = cost;
		this.deadline = deadline;
		this.compositeService = compositeService;
		this.crowdService = crowdService;
	}

	@Override
	public String toString() {
		return "MicroTask [id=" + id + ", template=" + template + ", consumer="
				+ consumer + ", cost=" + cost + ", deadline=" + deadline
				+ ", compositeService=" + compositeService + ", crowdService="
				+ crowdService + "]";
	}
	
}

package edu.fudan.se.bean;

import java.util.Date;

public final class WorkerResponse {
	public final long id;
	public final String worker;
	public final String responseString;
	public final long taskid;
	public final Date date;
	

	public WorkerResponse(long id, String worker, String responseString,
			long taskid,Date date) {
		this.id = id;
		this.worker = worker;
		this.responseString = responseString;
		this.taskid = taskid;
		this.date = date;
	}


	@Override
	public String toString() {
		return "WorkerResponse [id=" + id + ", worker=" + worker
				+ ", responseString=" + responseString + ", taskid=" + taskid
				+ ", date=" + date + "]";
	}
 
	
}

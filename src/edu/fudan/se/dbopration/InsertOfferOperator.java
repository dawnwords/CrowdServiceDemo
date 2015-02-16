package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertOfferOperator extends BaseDBOperator<Boolean> {

	private String worker;
	private long taskid;
	private int offer;

	public InsertOfferOperator(String worker, long taskId, int offer) {
		this.worker = worker;
		this.taskid = taskId;
		this.offer = offer;
	}

	@Override
	protected Boolean processData(Connection conn) throws Exception {
		String sql = "insert into workerresponse (worker, taskid, offer) values (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, worker);
		ps.setLong(2, taskid);
		ps.setInt(3, offer);
		if (ps.executeUpdate() == 0) {
			throw new Exception(
					"insertResponse(): error occured when inserting.");
		}
		return true;
	}

}

package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertResponseOperator extends BaseDBOperator<Boolean> {

	private String worker;
	private long taskid;
	private String response;

	public InsertResponseOperator(String worker, long taskid, String response) {
		this.worker = worker;
		this.taskid = taskid;
		this.response = response;
	}

	@Override
	protected Boolean processData(Connection conn) throws Exception {
		String sql = "select * from workerresponse where worker=? and taskid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, worker);
		ps.setLong(2, taskid);

		ResultSet rs = ps.executeQuery();

		if (rs.last()) {
			sql = "update workerresponse set responseString=?, answerTime=now() where worker=? and taskid=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, response);
			ps.setString(2, worker);
			ps.setLong(3, taskid);
			if (ps.executeUpdate() == 0) {
				throw new Exception(
						"insertResponse(): error occured when updating.");
			}
		} else {
			sql = "insert into workerresponse (worker, taskid, responseString, answerTime) values (?, ?, ?, now())";
			ps = conn.prepareStatement(sql);
			ps.setString(1, worker);
			ps.setLong(2, taskid);
			ps.setString(3, response);
			if (ps.executeUpdate() == 0) {
				throw new Exception(
						"insertResponse(): error occured when inserting.");
			}
		}
		return true;
	}

	public static void main(String[] args) {
		new InsertResponseOperator("yhbgg@10.131.253.172:1099/JADE", 80,
				"hahahahhahahhahaha").getResult();
	}
}

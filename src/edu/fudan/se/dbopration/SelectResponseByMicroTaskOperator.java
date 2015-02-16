package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import edu.fudan.se.bean.WorkerResponse;

public class SelectResponseByMicroTaskOperator extends
		BaseDBOperator<List<WorkerResponse>> {

	private long taskId;

	public SelectResponseByMicroTaskOperator(long taskId) {
		this.taskId = taskId;
	}

	@Override
	protected List<WorkerResponse> processData(Connection connection)
			throws Exception {
		List<WorkerResponse> result = new LinkedList<WorkerResponse>();

		String sql = "select id,worker,responseString,taskid,answerTime from workerresponse "
				+ "where taskid = ? and isSelected=1 and answerTime is not null";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, taskId);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			long id = rs.getLong(1);
			String worker = rs.getString(2);
			String responseString = rs.getString(3);
			long taskId = rs.getLong(4);
			Date date = rs.getDate(5);
			result.add(new WorkerResponse(id, worker, responseString, taskId,
					date));
		}
		return result;
	}

}

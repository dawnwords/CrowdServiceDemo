package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import edu.fudan.se.bean.AgentInfo;
import edu.fudan.se.bean.AgentOffer;
import edu.fudan.se.bean.MicroTask;

public class SelectOfferByMicroTaskOperator extends
		BaseDBOperator<List<AgentOffer>> {

	private MicroTask task;

	public SelectOfferByMicroTaskOperator(MicroTask task) {
		this.task = task;
	}

	@Override
	protected List<AgentOffer> processData(Connection connection)
			throws Exception {
		List<AgentOffer> result = new LinkedList<AgentOffer>();

		String sql = "select guid,capacity,longitude,latitude,reputation,offer from agentinfo,workerresponse "
				+ "where workerresponse.taskid=? and agentinfo.guid = workerresponse.worker";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setLong(1, task.id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String guid = rs.getString(1);
			String capacity = rs.getString(2);
			long longitude = rs.getLong(3);
			long latitude = rs.getLong(4);
			double reputation = rs.getDouble(5);
			int offer = rs.getInt(6);
			result.add(new AgentOffer(guid, capacity, longitude, latitude,
					reputation, offer));
		}
		return result;
	}
}

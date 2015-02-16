package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertCapacityOperator extends BaseDBOperator<Boolean> {

	private String worker;
	private String capacity;

	public InsertCapacityOperator(String worker, String capacity) {
		this.worker = worker;
		this.capacity = capacity;
	}

	@Override
	protected Boolean processData(Connection conn) throws Exception {
		String sql = "select * from agentinfo where guid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, worker);
		ResultSet rs = ps.executeQuery();
		sql = rs.last() ? "update agentinfo set isOnline=1,lastActive=now(),capacity=? where guid=?"
				: "insert into agentinfo(capacity,guid,isOnline,lastActive) values (?, ?, 1, now())";
		ps = conn.prepareStatement(sql);
		ps.setString(1, capacity);
		ps.setString(2, worker);
		return ps.executeUpdate() > 0;
	}

}

package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateOnlineOperator extends BaseDBOperator<Void> {

	private int timeout;

	public UpdateOnlineOperator(int timeout) {
		this.timeout = timeout;
	}

	@Override
	protected Void processData(Connection conn) throws Exception {
		String sql = "update agentinfo set isOnline=0 where now() > date_add(lastActive, INTERVAL ? second)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, timeout);
		ps.executeUpdate();
		return null;
	}

}

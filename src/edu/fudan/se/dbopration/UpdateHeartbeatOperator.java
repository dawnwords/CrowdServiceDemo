package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UpdateHeartbeatOperator extends BaseDBOperator<Boolean> {
	
	private double longitude;
	private double latitude;
	private String worker;

	public UpdateHeartbeatOperator(double longitude, double latitude,
			String worker) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.worker = worker;
	}

	@Override
	protected Boolean processData(Connection conn) throws Exception {
		String sql = "select * from agentinfo where guid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, worker);
		ResultSet rs = ps.executeQuery();
		if (rs.last()) {
			sql = "update agentinfo set longitude=? ,isOnline=1, lastActive=now(), latitude=? where guid=?";
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, longitude);
			ps.setDouble(2, latitude);
			ps.setString(3, worker);
			if (ps.executeUpdate() == 0) {
				throw new Exception("Error occured in updateWorkerInfo().");
			}
		} else {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		new UpdateHeartbeatOperator(120, 31, "yhbgg@10.131.253.172:1099/JADE").getResult();
	}
}

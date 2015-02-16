package edu.fudan.se.dbopration;

import jade.core.AID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectOnlineAgentIDOperator extends BaseDBOperator<List<AID>> {

	@Override
	protected List<AID> processData(Connection conn) throws Exception {
		List<AID> agentIds = new ArrayList<AID>();

		String sql = "select guid from agentinfo where isOnline=1";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			agentIds.add(new AID(rs.getString(1), false));
		}
		return agentIds;
	}

}

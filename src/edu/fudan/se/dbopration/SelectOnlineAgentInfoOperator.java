package edu.fudan.se.dbopration;

import jade.core.AID;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import edu.fudan.se.bean.AgentInfo;

/*
 * @author Jiahuan Zheng 201503051621
 * 
 * */

public class SelectOnlineAgentInfoOperator extends
		BaseDBOperator<ArrayList<AgentInfo>> {

	@Override
	protected ArrayList<AgentInfo> processData(Connection conn)
			throws Exception {
		ArrayList<AgentInfo> agentInfos = new ArrayList<AgentInfo>();

		String sql = "select guid,capacity,longitude,latitude,reputation from agentinfo where isOnline=1";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			agentInfos.add(new AgentInfo(rs.getString(1), 
					rs.getString(2),
					rs.getLong(3),
					rs.getLong(4),
					rs.getDouble(5)));
		}
		return agentInfos;
	}

}

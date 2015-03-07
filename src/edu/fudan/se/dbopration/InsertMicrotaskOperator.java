package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class InsertMicrotaskOperator extends BaseDBOperator<Long> {
	private String template;
	private String consumerId;
	private int cost;
	private int deadline;
	private String compositeService;
	private String crowdService;
	private int resultNum;
	private double latitude;
	private double longitude;

	public InsertMicrotaskOperator(String template, String consumerId,
			int cost, int deadline, String compositeService, String crowdService,int resultNum,double latitude,double longitude) {
		this.template = template;
		this.consumerId = consumerId;
		this.cost = cost;
		this.deadline = deadline;
		this.compositeService = compositeService;
		this.crowdService = crowdService;
		this.resultNum = resultNum;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	protected Long processData(Connection connection) throws Exception {
		long ret = 0;
		String sql = "insert into microtask(template,consumer,cost,deadline,createTime,compositeService,crowdService,resultNum,longitude,latitude) values(?,?,?,?,now(),?,?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, template);
		ps.setString(2, consumerId);
		ps.setInt(3, cost);
		ps.setInt(4, deadline);
		ps.setString(5, compositeService);
		ps.setString(6, crowdService);
		ps.setInt(7,resultNum);
		ps.setDouble(8,longitude);
		ps.setDouble(9,latitude);
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			ret = (Long) rs.getObject(1);
		}
		return ret;
	}

}

package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class InsertMicrotaskOperator extends BaseDBOperator<Long> {
	private String tempalte;
	private String consumerId;
	private int cost;
	private int deadline;
	private String compositeService;
	private String crowdService;

	public InsertMicrotaskOperator(String tempalte, String consumerId,
			int cost, int deadline, String compositeService, String crowdService) {
		this.tempalte = tempalte;
		this.consumerId = consumerId;
		this.cost = cost;
		this.deadline = deadline;
		this.compositeService = compositeService;
		this.crowdService = crowdService;
	}

	@Override
	protected Long processData(Connection connection) throws Exception {
		long ret = 0;
		String sql = "insert into microtask(template,consumer,cost,deadline,createTime,compositeService,crowdService) values(?,?,?,?,now(),?,?)";
		PreparedStatement ps = connection.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, tempalte);
		ps.setString(2, consumerId);
		ps.setInt(3, cost);
		ps.setInt(4, deadline);
		ps.setString(5, compositeService);
		ps.setString(6, crowdService);
		ps.executeUpdate();

		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
			ret = (long) rs.getObject(1);
		}
		return ret;
	}

}

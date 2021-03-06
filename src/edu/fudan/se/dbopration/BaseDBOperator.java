package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDBOperator<T> {
	private final static String url = "jdbc:mysql://10.131.252.156:3306/crowdservice?user=root&password=cloudfdse";

	private Connection connect() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		return DriverManager.getConnection(url);
	}

	public T getResult() {
		Connection conn = null;
		T result = null;

		try {
			conn = connect();
			result = processData(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	protected abstract T processData(Connection connection) throws Exception;

}

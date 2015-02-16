package edu.fudan.se.dbopration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDBOperator<T> {
	private final static String url = "jdbc:mysql://localhost:3306/crowdservice?user=root&password=";

	private Connection connect() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection(url);

		return conn;
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

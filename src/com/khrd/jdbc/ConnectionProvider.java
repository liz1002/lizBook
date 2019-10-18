package com.khrd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	public static Connection getConnection() throws SQLException {
		String jdbcDriver = "jdbc:apache:commons:dbcp:liz_book"; //커넥션 풀의 이름 (고정 앞부분 + DB명)
		return DriverManager.getConnection(jdbcDriver);
	}
}

package com.khrd.jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInit extends HttpServlet{

	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}

	private void loadJDBCDriver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initConnectionPool(){
		try {
			String url = "jdbc:mysql://localhost:3306/liz_book";
			String userid = "root";
			String userpwd = "rootroot";
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(url, userid, userpwd);
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			
			poolableConnFactory.setValidationQuery("select 1");
				/* 커넥션을 검사할 떄 사용할 쿼리를 설정
				bad 커넥션을 dbcp의 connection pool에선 여전히 가지고 있는 상태일 때,
				DB관련 프로그램이 호출되면 커넥션 관련 에러가 발생
				java에서 DB를 사용하기 전에 해당 connection이 정상적인지 검사 하도록 하는 것
				이것이 아래의 옵션이다.
				우리가 따로 커리를 연결할 수 있지만, select 1은 Microsoft SQL Server에서 권장하는 방법 */
				
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5); //5분마다 검사
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4); //사용할 수 있는 최소 커넥션 수
			poolConfig.setMaxTotal(50); //최대 커넥션 개수
			
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("liz_book", connectionPool); //생성한 커넥션 이름(DB이름과 동일하게 사용)
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.khrd.dto.User;
import com.khrd.jdbc.JDBCUtil;

public class UserDAO {
	private static final UserDAO dao = new UserDAO();
	
	private UserDAO() {}
	
	public static UserDAO getInstance() {
		return dao;
	}
	
	public User selectById(Connection conn, String uId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			String sql = "select * from user where u_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				User user = new User(
								rs.getString("u_id"), 
								rs.getString("u_name"), 
								rs.getString("u_password"));
				return user;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return null;
	}//selectById
	
	
	
}


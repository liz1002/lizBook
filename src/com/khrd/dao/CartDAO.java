package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.khrd.dto.Cart;
import com.khrd.dto.Product;
import com.khrd.jdbc.JDBCUtil;

public class CartDAO {
	private static final CartDAO dao = new CartDAO();
	
	private CartDAO() {}
	
	public static CartDAO getInstance() {
		return dao;
	}
	
	public int insertCart(Connection conn, Cart cart) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into cart values (null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cart.getcCount());
			pstmt.setInt(2, cart.getProduct().getpNo());
			pstmt.setString(3, cart.getuId());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//insertCart
	
	public List<Cart> selectList(Connection conn, String uId){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from cart left join product using(p_no) where u_id=?";					
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uId);
			rs = pstmt.executeQuery();
			ArrayList<Cart> list = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product(
									rs.getInt("p_no"),
									rs.getString("p_title"),
									rs.getString("p_name"),
									rs.getInt("p_price"), 
									rs.getString("p_detail"), 
									rs.getString("p_publisher"), 
									rs.getDate("p_regdate"), 
									rs.getString("p_file"),
									null); //type은 필요 없어서 null
				Cart cart =  new Cart(
									rs.getInt("c_no"),
									rs.getInt("c_count"), 
									p,
									rs.getString("u_id"));
				list.add(cart);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return null;
	}//selectList
	
	public int deleteCart(Connection conn, int cNo) {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from cart where c_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cNo);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}//deleteCart
	
	
	
	
}

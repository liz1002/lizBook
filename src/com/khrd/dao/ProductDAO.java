package com.khrd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.khrd.dto.Product;
import com.khrd.dto.Type;
import com.khrd.jdbc.JDBCUtil;

public class ProductDAO {
	private static final ProductDAO dao = new ProductDAO();
	
	private ProductDAO() {}
	
	public static ProductDAO getInstance() {
		return dao;
	}
	
	public List<Product> selectList(Connection conn){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from product left join type using(t_no)";					
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Product> list = new ArrayList<>();
			while (rs.next()) {
				Product product = new Product(
											rs.getInt("p_no"),
											rs.getString("p_title"),
											rs.getString("p_name"),
											rs.getInt("p_price"), 
											rs.getString("p_detail"), 
											rs.getString("p_publisher"), 
											rs.getDate("p_regdate"), 
											rs.getString("p_file"),
											new Type(rs.getInt("t_no"), rs.getString("t_name")));
				list.add(product);
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
	
	public Product selectByno(Connection conn, int pNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from product left join type using(t_no) where p_no=?";					
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Product product = new Product(
											rs.getInt("p_no"),
											rs.getString("p_title"),
											rs.getString("p_name"),
											rs.getInt("p_price"), 
											rs.getString("p_detail"), 
											rs.getString("p_publisher"), 
											rs.getDate("p_regdate"), 
											rs.getString("p_file"),
											new Type(rs.getInt("t_no"), rs.getString("t_name")));
				return product;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
		}
		return null;
	}//selectByno
	
	public int insertProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into product values (null, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getpTitle());
			pstmt.setString(2, product.getpName());
			pstmt.setInt(3, product.getpPrice());
			pstmt.setString(4, product.getpDetail());
			pstmt.setString(5, product.getpPublisher());
			pstmt.setTimestamp(6, new Timestamp(product.getpRegdate().getTime()));
			pstmt.setString(7, product.getpFile());
			pstmt.setInt(8, product.getType().gettNo());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt);
		}
		return -1;
	}
}

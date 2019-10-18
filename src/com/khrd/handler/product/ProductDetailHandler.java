package com.khrd.handler.product;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ProductDAO;
import com.khrd.dto.Product;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class ProductDetailHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int pNo = Integer.parseInt(req.getParameter("no"));
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			ProductDAO dao = ProductDAO.getInstance();
			Product product = dao.selectByno(conn, pNo);
			System.out.println(product);
			req.setAttribute("p", product);
			return "/WEB-INF/view/product/detail.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}
		return null;
	}
}

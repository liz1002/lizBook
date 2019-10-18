package com.khrd.handler.cart;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.CartDAO;
import com.khrd.dto.Cart;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class CartViewHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		String uId = (String) session.getAttribute("Auth");
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			CartDAO dao = CartDAO.getInstance();
			List<Cart> list = dao.selectList(conn, uId);
			req.setAttribute("list", list);
			return "/WEB-INF/view/cart/cartView.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}		
		return null;
	}

}

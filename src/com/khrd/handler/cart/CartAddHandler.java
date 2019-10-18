package com.khrd.handler.cart;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.CartDAO;
import com.khrd.dto.Cart;
import com.khrd.dto.Product;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class CartAddHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int cCount = Integer.parseInt(req.getParameter("count"));
		int pNo = Integer.parseInt(req.getParameter("pNo"));
		String uId = (String) req.getSession().getAttribute("Auth");
		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			CartDAO dao = CartDAO.getInstance();
			
			Product p = new Product();
			p.setpNo(pNo);
			
			Cart cart = new Cart(0, cCount, p, uId);
			int result = dao.insertCart(conn, cart);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(map);
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn);
		}		
		return null;
	}

}

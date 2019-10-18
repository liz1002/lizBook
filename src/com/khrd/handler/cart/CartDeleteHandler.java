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
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class CartDeleteHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int cNo = Integer.parseInt(req.getParameter("no"));
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			CartDAO dao = CartDAO.getInstance();
			int result = dao.deleteCart(conn, cNo);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(map);
			resp.setContentType("application/json;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.print(json);
			out.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(conn);
		}
		return null;
	}	
}

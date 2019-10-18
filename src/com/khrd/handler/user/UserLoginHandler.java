package com.khrd.handler.user;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.UserDAO;
import com.khrd.dto.User;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;

public class UserLoginHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/user/loginForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			String uId = req.getParameter("id");
			String uPassword = req.getParameter("password");
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				UserDAO dao = UserDAO.getInstance();
				User user = dao.selectById(conn, uId);
				if(user == null) { //없는 아이디
					req.setAttribute("result", "none");
					return "/WEB-INF/view/user/loginForm.jsp";
				}else if(user.getuPassword().equals(uPassword) == false) { //패스워드 불일치
					req.setAttribute("result", "notMatch");
					return "/WEB-INF/view/user/loginForm.jsp";
				}else { //로그인 처리(세션에 저장)
					HttpSession session = req.getSession();
					session.setAttribute("Auth", user.getuId());
					resp.sendRedirect(req.getContextPath() + "/product/list.do");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.close(conn);
			}
		}
		return null;
	}
}

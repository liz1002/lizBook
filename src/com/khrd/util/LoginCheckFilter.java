package com.khrd.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		// 로그인 상태 X - 로그인 화면으로 이동
		if(session == null || session.getAttribute("Auth") == null) {
			HttpServletResponse response = (HttpServletResponse) res;
			response.sendRedirect(request.getContextPath() + "/login.do");
		}
		// 로그인 상태 O - 진행
		else {
			chain.doFilter(req, res);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}

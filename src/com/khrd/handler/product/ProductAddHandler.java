package com.khrd.handler.product;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khrd.controller.CommandHandler;
import com.khrd.dao.ProductDAO;
import com.khrd.dto.Product;
import com.khrd.dto.Type;
import com.khrd.jdbc.ConnectionProvider;
import com.khrd.jdbc.JDBCUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductAddHandler implements CommandHandler{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if(req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/product/addForm.jsp";
		}else if(req.getMethod().equalsIgnoreCase("post")) {
			
			String uploadPath = req.getRealPath("upload");
			File dir = new File(uploadPath);
			if(dir.exists() == false) {
				dir.mkdir();
			}
			int size = 1024*1024*10; //10m
					
			MultipartRequest multi = new MultipartRequest(req,
														uploadPath,
														size,
														"UTF-8",
														new DefaultFileRenamePolicy());
			String pTitle = multi.getParameter("title");
			int pPrice = Integer.parseInt(multi.getParameter("price"));
			String pName = multi.getParameter("name");
			String pPublisher = multi.getParameter("publisher");
			String regdate =  multi.getParameter("regdate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date pRegdate = sdf.parse(regdate);
			int type = Integer.parseInt(multi.getParameter("type"));
			String pDetail = multi.getParameter("detail");
			String pFile = multi.getFilesystemName("file");
			
			Connection conn = null;
			try {
				conn = ConnectionProvider.getConnection();
				ProductDAO dao = ProductDAO.getInstance();
				Product product = new Product(0, pTitle, pName, pPrice, pDetail, pPublisher, pRegdate, pFile, new Type(type, null));
				dao.insertProduct(conn, product);
				resp.sendRedirect(req.getContextPath()+"/product/list.do");
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCUtil.close(conn);
			}
			
		}
		return null;
	}

}

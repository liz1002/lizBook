<%@page import="com.khrd.jdbc.JDBCUtil"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Connection conn = null;
		try{
			String jdbcDriver = "jdbc:apache:commons:dbcp:liz_book"; //커넥션 풀의 이름 (고정 앞부분 + DB명)
			conn = DriverManager.getConnection(jdbcDriver);
			if(conn != null){
				out.print("커넥션 풀에 연결했습니다.");
			}else{
				out.print("커넥션 풀에 연결을 실패했습니다.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn);		
		}
	%>
</body>
</html>
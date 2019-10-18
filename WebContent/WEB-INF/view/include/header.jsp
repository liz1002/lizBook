<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- css파일 추가 -->
<style>
	*{
		margin: 0;
		padding: 0;
	}
	div#header{
		width: 1000px;
		margin: 20px auto;
	}
	h1{
		text-align: center;
		font-size: 45px;
		text-shadow: 1px 1px 3px #DE7F94;
		margin: 20px 0px;
	}
	a{
		text-decoration: none;
		color: white;
	}
	a.btns{
		color: white;
		float: right;
		background: pink;
		border-radius: 25px;
		padding: 5px 10px;
		margin: 5px;
	}
	span#hello{
		float: right;
		padding: 10px;
		color: #999;
	}
	span#uName{
		color: pink;
		font-weight: bold;
	}
	div#menu{
		width: 100%;
		height: 50px;
		margin: 40px 0px 50px;
		background: pink;
		border-radius: 25px;
		clear: both;
	}
	#menu>ul{
		width: 1000px;
		height: 100%;
		margin: 0 auto;
		overflow: hidden;
		list-style: none;
	}
	#menu>ul>li{
		width: 16.6%;
		height: 100%;
		float: left;
		text-align: center;
	}
	#menu>ul>li>a{
		display: inline-block;
		width: 100%;
		height: 100%;
		line-height: 50px;
		color: white;
		font-size: 18px;
		text-shadow: 1px 1px 2px #A2485B;
		font-weight: 550;    
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<div id="header">
		<h1><a href="${pageContext.request.contextPath}/product/list.do">LIZ BOOK</a></h1>
		<c:if test="${Auth == null}">
			<a href="#" class="btns">회원가입</a>
			<a href="${pageContext.request.contextPath}/user/login.do" class="btns">로그인</a>
		</c:if>
		<c:if test="${Auth != null}">
			<a href="${pageContext.request.contextPath}/user/logout.do" class="btns">로그아웃</a>
			<a href="${pageContext.request.contextPath}/cart/view.do" class="btns">장바구니</a>
			<span id="hello"><span id="uName">${Auth}</span> 님, 반갑습니다.</span>
		</c:if>
		<div id="menu">
			<ul>
				<li><a href="#">베스트</a></li>
				<li><a href="#">신상품</a></li>
				<li><a href="#">IT</a></li>
				<li><a href="#">인문</a></li>
				<li><a href="#">문학</a></li>
				<li><a href="#">여행</a></li>
			</ul>
		</div>
	</div>
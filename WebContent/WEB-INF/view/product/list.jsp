<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
	ul#pList{
		width: 1000px;
		margin: 0 auto;
		list-style: none;
		overflow: hidden;
	}
	#pList>li{
		width: 200px;
		height: 450px;
		float: left;
		margin: 10px 25px 20px;
	}
	#pList>li>a{
		text-decoration: none;
	}
	img{
		width: 100%;
		height: 
	}
	span.title{
		color: #000;
		font-size: 18px;
		font-weight: bold;
	}
	span.name{
		color: #333;
		font-size: 12px;
	}
	span.price{
		color: #000;
		font-size: 16px;
		font-weight: bold;
	}
</style>
<ul id="pList">
	<c:forEach var="p" items="${list}">
		<li>
			<a href="${pageContext.request.contextPath}/product/detail.do?no=${p.pNo}">
				<img src="${pageContext.request.contextPath}/upload/${p.pFile}">
				<span class="title">${p.pTitle}</span><br>
				<span class="name">${p.pName} | ${p.pPublisher}</span><br>
				<span class="price">${p.pPrice}</span>
			</a>          
		</li>
	</c:forEach>
</ul>
<%@ include file="../include/footer.jsp" %>
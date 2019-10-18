<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
	div#container{
		width: 1000px;
		margin: 0 auto;
		padding: 0px 40px;
	}
	table{
		width: 920px;
		margin: 0 auto;
		border-collapse: collapse;
		text-align: center; 
	}
	th,td{
		border: #aaa 1px solid;
		padding: 5px 10px;
	}
	td.title{
		text-align: left;
	}
	img{
		width: 80px;
	}
 	p#sumPrice{
 		text-align: right;
 	}
	span#sum{
		font-size: 20px;
		font-weight: bold;
		color: #B185AB;
		margin-right: 40px;
	}
</style>
<div id="container">
	<table>
		<tr>
			<th></th>
			<th>상품이름</th>
			<th>상품개수</th>
			<th>가격</th>
			<th></th>
		</tr>
		<c:set var="sum" value="0"/>
		<c:forEach var="c" items="${list}">	
			<tr> 
				<td>
					<img src="${pageContext.request.contextPath}/upload/${c.product.pFile}">
				</td>
				<td class="title">${c.product.pTitle}</td>
				<td>
					<input type="text" value="${c.cCount}" size="1"><br>     
					<button id="btnUpd">변경</button>
				</td>
				<td>
					<fmt:formatNumber value="${c.cCount * c.product.pPrice}" pattern="###,###"/>
				</td>
				<c:set var="sum" value="${sum + c.cCount * c.product.pPrice}"/>
				<td>
					<button class="btnDel" data-cNo="${c.cNo}" data-cPrice="${c.cCount * c.product.pPrice}">삭제</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p id="sumPrice">전체 가격 : <span id="sum"><fmt:formatNumber value="${sum}" pattern="###,###"/></span></p>
</div>

<script>

	//상품 삭제
	$(".btnDel").click(function() {
		var target = $(this);
		var cNo = target.attr("data-cNo"); //삭제할 상품 번호
		var cPrice = target.attr("data-cPrice"); //삭제할 상품 가격
		var sum = Number($("#sum").text()); //전체 가격
		
		$.ajax({
			url: "${pageContext.request.contextPath}/cart/delete.do",
			type: "get",
			data: {"no" : cNo},
			dataType: "json",
			success : function(res) {
				console.log(res);
				target.parent().parent().remove(); //화면에서 상품 삭제
				sum -= cPrice;
				$("#sum").text(sum); //전체 가격 변경
			}
		})
	})
</script>
<%@ include file="../include/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
	div#container{
		width: 920px;
		margin: 0 auto;
		padding: 0 40px;    
	}
	
	/* 사진, 제목, 정보  */
	div#top{
		width: 100%;
		overflow: hidden;
	}
	div#top>p{
		line-height: 220%;    
	}
	img{
		width: 300px;
		float: left; 
		margin-right: 40px;
	}   
	p#title{
		font-size: 25px;
		font-weight: bold;
		color: #84557D;
	}         
	span.label{
		display: inline-block;
		width: 100px;
	}
	span#price{
		font-size: 22px;
		font-weight: bold;
		color: #B185AB;
	}
	button{
		border-radius: 25px;
		background: #B185AB;
		color: white;
		padding: 5px 10px;
		border: none;
		outline: none;
	}
	/* 책 내용 설명  */
	div#bottom{
		width: 100%;
	}
	p#detail{
		width: 90%;
		margin: 50px 20px;
	}
</style>
<div id="container">
	<div id="top">
		<img src="${pageContext.request.contextPath}/upload/${p.pFile}">
		<p id="title">${p.pTitle}</p>
		<p>
			<span>${p.pName}</span> | 
			<span>${p.pPublisher}</span> |
			<span>${p.pRegdate}</span> | 
			<span>판매부수</span> 
		</p>
		<p>
			<span class="label">판매가</span>
			<span id="price"><fmt:formatNumber value="${p.pPrice}" pattern="###,###"/>원</span>
		</p>
		<p>
			<span class="label">상품개수</span>
			<select id="count">
				<c:forEach var="i" begin="1" end="10" step="1">
					<option>${i}</option>
				</c:forEach>
			</select>
		</p>
		<p>
			<button id="btnCart">장바구니</button>
			<button id="btnOrder">주문하기</button>
		</p>
	</div>
	<div id="bottom">
		<p id="detail">${p.pDetail}</p>
	</div>
</div>

<script>
	//장바구니 버튼
	$("#btnCart").click(function() {
		var count = $("#count").val();
		var pNo = ${p.pNo};
		
		$.ajax({
			url: "${pageContext.request.contextPath}/cart/add.do",
			type: "get",
			data: {"count" : count, "pNo" : pNo},
			dataType: "json",      
			success: function(res) {
				console.log(res);
				alert("상품을 장바구니에 담았습니다.");
				if(res.result == -1){
					alert("로그인 후 이용해주세요.");               
				}
			},
			error: function(e) {
				console.log(e);
			}
		})		
	})
</script>
<%@ include file="../include/footer.jsp" %>
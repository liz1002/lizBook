<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
	div#form{
		width: 500px; 
		margin: 0 auto;
		padding: 30px 0px; 
	}
	label{
		width: 200px;
		float: left;
		text-align: right;
		margin-right: 20px;
	}
	#submit{
		margin-left: 220px;               
	}
</style>
<div id="form">
	<form action="add.do" method="post" enctype="multipart/form-data">
		<p>
			<label>책 제목</label>
			<input type="text" name="title">
		</p>
		<p>
			<label>책 가격</label>
			<input type="text" name="price">
		</p>
		<p>
			<label>저자</label>
			<input type="text" name="name">
		</p>
		<p>
			<label>출판사</label>
			<input type="text" name="publisher">
		</p>
		<p>
			<label>출판날짜</label>
			<input type="date" name="regdate">
		</p>
		<p>
			<label>책 종류</label>
			<select name="type">
				<option value="1">IT</option>
				<option value="2">인문</option>
				<option value="3">문학</option>
				<option value="4">여행</option>
			</select>
		</p>
		<p>
			<label>책 사진</label>
			<input type="file" name="file">
		</p>
		<p>
			<label>상세 설명</label>
			<textarea name="detail" cols="30" rows="5"></textarea>
		</p>
		<p id="submit">
			<input type="submit" value="등록">
		</p>
	</form>
</div>
<%@ include file="../include/footer.jsp" %>
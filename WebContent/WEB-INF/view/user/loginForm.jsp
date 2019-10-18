<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<style>
	fieldset {
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
<script>
	$(function() {
		if("${result}" == "none"){
			alert("없는 아이디");
		}
	})
</script>
	<div>
<form action="login.do" method="post">
	<fieldset>
		<p>
			<label>아이디</label>
			<input type="text" name="id">
		</p>
		<p>
			<label>비밀번호</label>
			<input type="password" name="password">
		</p>
		<p id="submit">
			<input type="submit" value="로그인">
		</p>
	</fieldset>
</form>
<%@ include file="../include/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String url = request.getParameter("url"); //로그인후 이동할 주소
if(url == null){ url = "/goods/admin_index.jsp"; }
%>
<style>
<%@include file="../css/common.css"%>
<%@include file="../css/init.css"%>
main>div{
width:1000px;
margin: auto;
text-align: center;
}
</style>
<main>
	<div>
		<h2>로그인</h2>
		<form name = "frmLogin" action = "/goods/admin_login" method = "post">
		<input type = "hidden" name = "url" value = "<%=url %>">
		<div>아이디: <input type = "text" name = "uid" required value = "admin1"></div>
	
		<div>비밀번호: <input type = "password" name = "pwd" required value = "1234"><br></div>
		<input type = "submit" value = "로그인">
	</div>
</main>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.time.*" %>
<%@ page import="vo.*"%>
<%
String dbDriver = "com.mysql.cj.jdbc.Driver";
String dbUrl = "jdbc:mysql://localhost:3306/testt"+
"?useUnicode=true&characterEncoding=UTF8&"+
"verifyServerCertificate=false&useSSL=false&serverTimezone=UTC";
Connection conn = null; 
Statement stmt = null; 
ResultSet rs =null;
String sql = null;
try{
	Class.forName(dbDriver);
	conn = DriverManager.getConnection(dbUrl,"root","1234");
	stmt = conn.createStatement();
	sql = "select * from t_member_info";
	rs = stmt.executeQuery(sql);
	
}catch(Exception e){
	out.println("DB연결에 문제가 발생했습니다");
	e.printStackTrace();
} 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
while (rs.next()) {
	out.println(rs.getString("mi_id") + "<br />");
}
%>
</body>
</html>
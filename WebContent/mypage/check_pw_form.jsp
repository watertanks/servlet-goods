<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "/_inc/inc_head.jsp" %>
<%
if(loginInfo == null){
	out.println("<script>");
	out.println("alert('로그인 후 이용해주세요.');");
	out.println("history.back();");
	out.println("</script>");
	out.close();
}

%>

<script>
function nope(){
	alert('암호를 입력하신 후 이용하실 수 있습니다.');
}
</script>
<style>
main {	max-width: 1200px;
	margin: 0 auto;
	position: relative;
	top: 220px;
	}
.main{
	display:flex;
}
=
</style>
<main>
<body>
    <style>
    <%@include file ="../css/common.css" %>
	<%@include file ="./mypage.css"%>
        .form{
            text-align: center;
        }
        .form li{
            padding: 5px 0;
        }
        .form li > p{
            width: 30%;
            display: inline-block;
        }
        
    </style>
    <div class="wrapper">
        <h3>마이페이지 > 본인확인</h3>
        <div class="main">
	        <div class="menu">
	            <p onclick="nope()">내 정보</p>
	            <a onclick="nope()">내정보</a>
	            <a onclick="nope()">비밀번호변경</a>
	            <p onclick="nope()">주문정보</p>
	            <a onclick="nope()">장바구니</a>
	            <a onclick="nope()">주문내역</a>
	            <p onclick="nope()">포인트/쿠폰</p>
	            <a onclick="nope()">포인트내역</a>
	            <a onclick="nope()">쿠폰함</a>
	            <p onclick="nope()">기타</p>
	            <a onclick="nope()">문의내역</a>
	            <a onclick="nope()">회원탈퇴</a>
	        </div>
	        <div class="content">
	            <form action="/goods/pw_form" method="POST" name="frm" id="frm" class="form">
	                <div>
	                   	<p>
	                   	비밀번호 입력
	                   	</p>
	                    <input type="password" id="upw" name="upw" required />
	                </div>
	                <br>
	                <input type="submit" class="button" value="입력" /> 
	            </form>
	        </div>
        </div>
    </div>
<%@include file="/_inc/inc_foot.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "_inc/inc_head.jsp" %>
<%
request.setCharacterEncoding("utf-8");
String url = request.getParameter("url");	// 로그인 후 이동할 페이지 주소
if (url == null)	url = "home";
// 로그인 후 이동할 페이지 주소가 없으면 메인 화면으로 지정
%>
<link href="./css/login_form.css" rel="stylesheet">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<style>
main {	max-width: 1200px;
	margin: 0 auto;
	position: relative;
	top: 220px;
	}
</style>
<main>
    <article class="frmWrap">
        <h2>로그인</h2>
        <form name="frmlogin" action="login" method="post">
        <input type="hidden" name="url" value="<%=url %>" />
            <p>ID <input type="text" name="uid" value="test1" /></p>
            <p>PW <input type="password" name="pwd" value="1234"/></p>
            <span><input type="submit" value="로그인"></span>
        </form>
        <p><a href="#">회원가입</a>/<a href="#">아이디 & 비밀번호찾기</a></p>
        <div class="loginApi">
            <a href="" ><img src="/goods/img/naver.png" class="naverApi" /></a>
            <a class="btn btnkakao" id="kakao-login-btn"  href="javascript:kakaoLogin()">
            	<img style="width:200px;" src="/goods/img/Kakao.png" alt="카카오">
            	</a>
            <a href=""><img src="/goods/img/google.png" class="googleApi"/></a>
        </div>
    </article>
</main>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
Kakao.init('cd1ada7163660ca63c0cbaeff479524f');
function kakaoLogin() {
    Kakao.Auth.login({
		scope:'profile_nickname, account_email, gender, birthday, talk_message',
		success: function(authObj){
			console.log(authObj);
			Kakao.API.request({
				url:'/v2/user/me',
				success: function(res){
					const kakao_account = res.kakao_account;
					console.log(kakao_account);
					$.ajax({
						type : "POST",
						url : "/dupId",
						data : {"uid":email},
						
						
					})
				}
			});
		}
    
    }) 
}
        

function createHiddenLoginForm(uid){
	
	var frm = document.createElement('form');
	frm.setAttribute('method', 'post');
	frm.setAttribute('action', '/hidden_login');
	var hiddenInput = document.createElement('input');
	hiddenInput.setAttribute('type','hidden');
	hiddenInput.setAttribute('name','uid');
	hiddenInput.setAttribute('value',uid);
	frm.appendChild(hiddenInput);
	document.body.appendChild(frm);
	frm.submit();
}
</script>
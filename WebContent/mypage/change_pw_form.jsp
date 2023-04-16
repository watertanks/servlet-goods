<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "/_inc/inc_head.jsp" %>
<%
String uid = loginInfo.getMi_id();
%>



<style>
main {	max-width: 100%;
	margin: 0 auto;
	position: relative;
	top: 220px;}
<%@include file ="../css/common.css"%>
<%@include file ="./mypage.css"%>	

.main{
	display:flex;
	height: 100%;
}
.content{
	height: 100%;
}
.wrapper{
	width: 1200px;
	margin: 0 auto;
}
.btn{text-align: center;}
</style>
<main>
<body>
    <div class="wrapper">
        <h3>마이페이지 > 비밀번호 변경</h3>
		<div class="main">       
	        <div class="menu">
	               <p>내 정보</p>
		           <a href="/goods/my_page">내정보</a>
		           <a href="change_pw_form">비밀번호변경</a>
		           <p>주문정보</p>
		           <a href="/goods/cart_view">장바구니</a>
		           <a href="/goods/order_list">주문내역</a>
		           <p>포인트/쿠폰</p>
		           <a href="/goods/point_list">포인트내역</a>
		           <a href="/goods/coupon_list">쿠폰함</a>
		           <p>기타</p>
		           <a href="mantoman_list.html">문의내역</a>
		           <a href="member_out_form.html">회원탈퇴</a>
	        </div>
	
	        <div class="content">
	            <form name="form" id="form" class="form" method="POST">
	            <input type="hidden" id="uid" name="uid" value="<%=uid %>">
	            <input type="hidden" id="chkOldPwd" value="n" />
	            <input type="hidden" id="chkNewPwd" value="n" />
	            <input type="hidden" id="chkPw" value="n"/>
	                <ul>
	                    <li>
	                    	<p style="width:50px;"></p>
	                    	<p>기존비밀번호</p>
	                        <input type="password" id="oldPwd" name="oldPwd" style="width:220px;" onkeyup="chkDupOldPw(this.value)" required>
	                        <span style="font-size:5px; text-align:center; margin: 0 auth; padding:0; width:180px;" name="msgOldPwd" id="msgOldPwd">기존 비밀번호를 입력해주세요.</span>
	                    </li>
	                    <li>
	                    	<p style="width:50px;"></p>
	                    	<p>신규비밀번호</p>
	                        <input type="password" id="newPwd" name="newPwd" style="width:220px;" onkeyup="chkDupNewPw(this.value)" required>
	                        <span style="font-size:5px; text-align:center; margin: 0 auth; padding:0; width:180px;" name="msgNewPwd" id="msgNewPwd">신규 비밀번호를 입력해주세요.</span>
	                    </li>
	                    <li>
	                    	<p style="width:50px;"></p>
	                    	<p>비밀번호 확인</p>
	                        <input type="password" id="newPwdChk" style="width:220px;" onkeyup="chkPwd(this.value)"required>
	                        <span id="chkPwd"style="font-size:5px; text-align:center; margin: 0 auth; padding:0; width:180px;">신규 비밀번호를 다시 입력해주세요.</span>
	                        
	                    </li>
	                </ul>
	                <!-- <div class="btn">
	                    <input type = "reset" class="button" value="취소하기">
	                    <input type = "submit" class="button" value="변경하기">
	                </div> -->
	            </form>
	            <div class="btn">
	                <input type="button" value="수정하기" onclick="chkAllPwd()"/></button>
	            </div>
	        </div>
	     </div>   
    </div>
</body>
</main>
<script>
function nope(){
	alert('암호를 입력하신 후 이용하실 수 있습니다.');
}

function chkDupOldPw(oldPwd) {
	if (oldPwd.length >= 4) { 
		$.ajax({
			type : "POST", 
			url : "/goods/dupOldPwd", 
			data : {
				"uid" : $('#uid').val(),
				"oldPwd" : oldPwd
			}, 
			success : function(chkRs) {
				if (chkRs == 1) {
					$("#msgOldPwd").html("<span style='color: blue;'>비밀번호가 일치합니다.</span>");
					$("#chkOldPwd").val('y');
				}else{
					$("#msgOldPwd").html("<span style='color: red;'>비밀번호가 틀렸습니다.</span>");
					$("#chkOldPwd").val('n');
				}
				
			}
		});
	} else { 
		$("#msgOldPwd").text("기존 비밀번호를 정확히 입력해주세요");
		$("#chkOldPwd").val('n');
		
	}
};
function chkDupNewPw(newPwd) {
	if (newPwd.length >= 4) { 
		$.ajax({
			type : "POST", 
			url : "/goods/dupNewPwd", 
			data : {
				"uid" : $('#uid').val(),
				"newPwd" : newPwd
			}, 
			success : function(chkRs) {
				if (chkRs == 1) {
					$("#msgNewPwd").html("<span style='color: blue;'>변경 가능한 비밀번호 입니다.</span>");
					$("#chkNewPwd").val('y'); 
				}else{
					$("#msgNewPwd").html("<span style='color: red;'>이미 사용중인 비밀번호입니다.</span>");
					$("#chkNewPwd").val('n');
				}
			}
		});
	} else { 
		$("#msgNewPwd").text("새로 사용할 비밀번호를 입력해주세요");
		$("#chkNewPwd").val('n');
		
	}
};
function chkPwd(rePwd){
	var rePwd = rePwd;
	var newPwd = $('#newPwd').val();
	if(newPwd == rePwd){
		$("#chkPwd").html("<span style='color: blue;'>새 비밀번호와 일치합니다.</span>");
		$("#chkPw").val('y'); 
	} else {
		$("#chkPwd").html("<span style='color: red;'>새 비밀번호를 정확히 다시 입력해주세요</span>");
		$("#chkPw").val('n');
	}
}
function chkAllPwd(){
	var oldPwd = $('#chkOldPwd');
	var newPwd = $('#chkNewPwd');
	var rePwd = $('#chkPw');
	var form = $('#form');
	if(oldPwd.val() != 'y'){
		alert('비밀번호가 틀렸습니다.');
		return;
	} else if(newPwd.val() != 'y') {
		alert('변경할 비밀번호를 다시 확인해주세요.');
		return;
	} else if(rePwd.val() != 'y'){
		alert('새 비밀번호를 정확히 다시 입력해주세요');
		return;
	}
	form.attr("method", "POST");
	form.attr("action", "/goods/changeInfoPw");
	form.submit();
}
</script>
</html>
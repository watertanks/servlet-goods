<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "/_inc/inc_head.jsp" %>

<%
	LocalDate today = LocalDate.now();   // 오늘 날짜
	int cyear = today.getYear();
	int cmonth = today.getMonthValue();
	int cday = today.getDayOfMonth();
	int last = today.lengthOfMonth();

	String[] arrDomain = {"naver.com", "daum.net", "nate.com", "gmail.com"};
	
	HttpSession session2 = request.getSession();
	MemberAddr memberAddr = (MemberAddr)session2.getAttribute("memberAddr");
	
	if(loginInfo == null){
		out.println("<script>");
		out.println("alert('로그인 후 이용해주세요.');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
	}
	String uid = loginInfo.getMi_id();				String name = loginInfo.getMi_name();
	String nickname = loginInfo.getMi_nickname();	String[] email = loginInfo.getMi_email().split("@");
	String e1 = email[0];	String e3 = email[1];	String zip = memberAddr.getMa_zip();
	String addr1 = memberAddr.getMa_addr1();		String addr2 = memberAddr.getMa_addr2();
	String[] phone = loginInfo.getMi_phone().split("-");
	String p1 = phone[0];	String p2 = phone[1];	String p3 = phone[2];
	String[] birth = loginInfo.getMi_birth().split("-");
	String y = birth[0], m = birth[1], d = birth[2];
	
	
			
%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js" ></script>
<script src="/goods/js/date_change.js"></script>
<link href="/goods/css/init.css" rel="stylesheet">
<link href="/goods/css/common.css" rel="stylesheet">

<style>
.wrapper {
	width: 1200px;
}
main { transform: translateY(400px); }
textarea {
	width: 90%;
	margin: 10px;
	height: 150px;
}
.form li p {text-align: center;}
</style>

<script>
function chageVal(v){
	$(this).value().put(v);
}
function chkAll(){
	// email 정규식
    let e1Reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])/i;
	let e2Reg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    // pw 정규식 : 특수문자,문자,숫자 포함 8~20자 이내
    let pwReg = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
   
	/*id 정규식 : 영문+숫자 조합 4~20자 이내.. 미사용
    let idReg = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{4,20}$/;*/
    
    // 전화번호 정규식
    let p2Reg = /^\d{4}$/;
    let p3Reg = /^\d{4}$/;
    // 우편번호가 적혔는지 확인용
    let postReg = $('#ma_zip').val();
    //console.log(postReg);
    // 이름 확인용
    let nameReg = $('input[name=mi_name]').val();
    // 닉네임 확인용
    let nicknameReg = $('input[name=mi_nickname]').val();
    
    let allReg = [nicknameReg, e1Reg, e2Reg, pwReg, p2Reg, p3Reg, postReg];
    
    if( nicknameReg.length < 2 || nicknameReg == '' || nicknameReg == null ){
        alert('닉네임을 다시 확인해주세요.')
        $('input[name=mi_nickname]').focus();
        return;
    }else if( nicknameReg.length > 20 ){
    	alert('닉네임을 다시 확인해주세요.')
        $('input[name=mi_nickname]').focus();
        return;
    }
    
    if( !e1Reg.test($('input[name=e1]').val()) ){
        alert('이메일을 다시 확인해주세요.')
        $('input[name=e1]').focus();
        return;
    }
    if( !e2Reg.test($('input[name=e2]').val()) ){
        alert('이메일을 다시 확인해주세요.')
        $('input[name=e2]').focus();
        return;
    }
        
    if( !p2Reg.test($('input[name=p2]').val()) ){
        alert('전화번호가 유효하지 않습니다.')
        $('input[name=p2]').focus();
        return;
    }
    if( !p3Reg.test($('input[name=p3]').val()) ){
        alert('전화번호가 유효하지 않습니다.')
        $('input[name=p3]').focus();
        return;
    }
    if( postReg.length < 5 || postReg == '' || postReg == null){
        alert('우편번호를 다시 확인해주세요.')
        $('input[name=sample6_postcode]').focus();
        return;
    }
    if(!$('input[name=chkEmail]').val('y')){
        alert('이메일 중복검사를 해주세요.')
        return;
    }
    $('.form').attr("action", "/goods/member_proc_up");
	$('.form').attr("method", "POST");
	$('.form').submit();
};
	
	function onlyNumber(){
	    if((event.keyCode<48)||(event.keyCode>57))
	       event.returnValue=false;
	};
	function putE3(){
		if ($("#e2").val() == "") {				// 선택 : '도메인' 일 때
				$("#e3").val("");
				$("#e3").prop("readonly", true);
		} else if ($("#e2").val() == "direct") {	// 선택 :'직접입력' 일 때
			$("#e3").val("");
			$("#e3").prop("readonly", false);
			$("#e3").focus();
		} else {								// 선택한게 값이 있는 것 일 때
			$("#e3").prop("readonly", true);
			$("#e3").val($("#e2").val());
		}
	};

	
	function chkDupNick(unick) {
		if (unick.length >= 2) { 
			$.ajax({
				type : "POST", 
				url : "/goods/dupNick", 
				data : {
					"unick" : unick
				}, 
				success : function(chkRs) { 
					var nickname = $('#mi_nickname').val();
					var chkNickName = $('#chkNickName').val();
					if (chkRs == 0 ) {
						$("#nickMsg").html("<p style='color: blue;'>변경 가능한 닉네임 입니다.</p>");
						$("#nickChk").val("y");
					} else if(chkRs != 0 && nickname == chkNickName){
						$("#nickMsg").html("<p style='color: green;'>현재 닉네임 입니다.</p>");
						$("#nickChk").val("y");
					} else if(chkRs != 0){ 
						$("#nickMsg").html("<p style='color: red;'>이미 사용중인 닉네임 입니다.</p>");
						$("#nickChk").val("n"); 
					}
				}
			});
		} else { 
			$("#nickMsg").html("<p style='color: black;'>닉네임은 2~20자 이내로 입력하세요.</p>");
			$("#nickChk").val("n");
			
		}
	};
</script>
<style>

</style>
<body>
<main>
	<div class="wrapper">
		<h3>내 정보</h3>
		<form class="form" name="frmJoin" id="frmJoin" method="POST" />
			<!-- 중복체크용 hidden -->
			<input type="hidden" name="idChk" value="n" />
			<input type="hidden" name="nickChk" value="n" />
			<input type="hidden" name="uemailChk" value="n" />
			<input type="hidden" id="chkNickName" value="<%=nickname %>" />
			<ul>
				<li>
					<p>이름</p> <input type="text" name="mi_name" id="mi_name" value="<%=name %>" disabled >
				</li>
				<li>
					<p>ID</p> <input type="text" name="mi_id" id="mi_id" maxlength="20" value="<%=uid %>" onkeyup="chkDupId(this.value);" disabled >
				</li>
				<li>
					<p>닉네임</p> <input type="text" name="mi_nickname" id="mi_nickname" value="<%=nickname %>"maxlength="20" onkeyup="chkDupNick(this.value);" placeholder="2 ~ 20자 이내" autocomplete="off">
					<p id="nickMsg" style="font-size:5px; color:green;" >현재 닉네임 입니다.</p>
				</li>
				<li>
					<p>이메일</p> 
					<input type="text" id="e1" name="e1" size="20" autocomplete="off" value="<%=e1 %>" disabled/> @ 
					<select name="e2" id="e2" onchange="putE3();" disabled >
						<option value="">도메인 선택</option>
						<%
							for (int i = 0; i < arrDomain.length; i++) {
						%>
						<option value="<%=arrDomain[i]%>"><%=arrDomain[i]%></option>
						<%
							}
						%>
						<option value="direct" selceted="selected" autocomplete="off" disabled>직접 입력</option>
					</select>
					<input type="text" name="e3" id="e3" size="10" value="<%=e3 %>" disabled readonly>
				</li>
				<li>
					<p>연락처</p> 
					<select name="p1">
						<option>010</option>
						<option>011</option>
						<option>016</option>
						<option>019</option>
					</select>- 
				<input type="text" name="p2" value="<%=p2 %>" size="4" maxlength="4" onkeypress="onlyNumber();" >-
				 <input type="text" name="p3" value="<%=p3 %>" size="4" maxlength="4" onkeypress="onlyNumber();" >
				</li>
				<li class="addrFinder">
					<p>주소</p>
					<input type="text" id="ma_zip" name="ma_zip" placeholder="우편번호" value="<%=zip %>" readonly />
					<input type="text" id="ma_addr1" name="ma_addr1" placeholder="주소" value="<%=addr1 %>" readonly />
					<input type="button"onclick="sample6_execDaumPostcode()" value="우편번호 찾기" />
					<input type="text" id="sample6_extraAddress" placeholder="" readonly><br />
					<p><input type="hidden" /></p>
					<input type="text" id="ma_addr2" name="ma_addr2" value="<%=addr2 %>" placeholder="상세주소" maxlength="20" style="width:250px;" readonly />
					
				</li>
					<script>
						function sample6_execDaumPostcode() {
							new daum.Postcode(
									{
										oncomplete : function(data) {
											
											// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

											// 각 주소의 노출 규칙에 따라 주소를 조합한다.
											// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
											var addr = ''; // 주소 변수
											var extraAddr = ''; // 참고항목 변수
											//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
											if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
												addr = data.roadAddress;
											} else { // 사용자가 지번 주소를 선택했을 경우(J)
												addr = data.jibunAddress;
											}

											// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
											if (data.userSelectedType === 'R') {
												// 법정동명이 있을 경우 추가한다. (법정리는 제외)
												// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
												if (data.bname !== ''
														&& /[동|로|가]$/g
																.test(data.bname)) {
													extraAddr += data.bname;
												}
												// 건물명이 있고, 공동주택일 경우 추가한다.
												if (data.buildingName !== ''
														&& data.apartment === 'Y') {
													extraAddr += (extraAddr !== '' ? ', '
															+ data.buildingName
															: data.buildingName);
												}
												// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
												if (extraAddr !== '') {
													extraAddr = ' ('
															+ extraAddr + ')';
												}
												// 조합된 참고항목을 해당 필드에 넣는다.
												document.getElementById("sample6_extraAddress").value = extraAddr;

											} else {
												document.getElementById("sample6_extraAddress").value = '';
											}
											
											// 우편번호와 주소 정보를 해당 필드에 넣는다.
											document.getElementById('ma_zip').value = data.zonecode;
											document.getElementById('ma_addr1').value = addr;
											// 커서를 상세주소 필드로 이동한다.
											document.getElementById('ma_addr2').focus();
											$('#ma_addr2').prop("readonly", false);
										}
									}).open();
						}
					</script>
				</li>
				<li>
					<p>
						생년월일 : 
					</p>
					<p style="width:210px;" >
					<select name="by" onchange="resetday(this.value, this.form.bm.value, this.form.bd);">
<% for (int i = 1930 ; i <= cyear ; i++) { %>
     					 <option value="<%=i %>" <% if (i == cyear) { %>selected="selected"<% } %>><%=i %></option>
<% } %>
					</select>년
  					<select name="bm" onchange="resetday(this.form.by.value, this.value, this.form.bd);">
<%
for (int i = 1 ; i <= 12 ; i++) {
   String bm = i + "";
   if (i < 10)   bm = "0" + i;
%>
   						<option  <% if (i == cmonth) { %>selected="selected"<% } %>><%=bm %></option>
<% } %>
   					</select>월
   					<select name="bd">
<%
for (int i = 1 ; i <= last ; i++) {
   String bd = i + "";
   if (i < 10)   bd = "0" + i;
%>
   						<option <% if (i == cday) { %>selected="selected"<% } %>><%=bd %></option>
<% } %>
   					</select>일
					</p>
				</li>
				<li>
					<p>성별</p> 
					남자<input type="radio" name="mi_gender" value="남" checked>
					여자<input type="radio" name="mi_gender" value="여" >
				</li>
			</ul>

			<div class="btn">
				<input onclick="history.back()" type="button" value="뒤로가기" class="button"  />
				<input onclick="chkAll()" type="button" value="수정하기" class="button" id="submit_button"/>
			</div>
		</form>
	</div>
</main>
</body>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</html>
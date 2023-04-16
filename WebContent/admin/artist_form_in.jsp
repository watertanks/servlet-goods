<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_inc/inc_admin.jsp" %>
<style>
<%@include file="../css/common.css"%>
<%@include file="../css/init.css"%>
</style>

<%
request.setCharacterEncoding("utf-8");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");


ArrayList<ArtistCode> bigList = (ArrayList<ArtistCode>)request.getAttribute("bigList");
ArrayList<ArtistProduct> smallList = (ArrayList<ArtistProduct>)request.getAttribute("smallList");
ArrayList<ArtistMember> artistMemberList = (ArrayList<ArtistMember>)request.getAttribute("artistMemberList");


/*
상품 등록폼 submit 목록(request)
ac 아티스트 코드
ap 소분류 코드
상품명 name
가격 price
할인 dc
특별상품 special(n, h, m)
판매기간 sdate(yymmdd)
option 멤버명 배열
stock 재고량 배열 
이미지 piimg1~3(파일)
이미지상세 pidesc (파일)
상품정보 text
게시여부 isview(y/n)
*/
%>
<script>


function select(value){
	let optList = document.querySelectorAll('.option_list');
	for(let i = 0;i<optList.length;i++){
		if(optList[i].id.substring(0,2) == document.insert.ac.value){
			optList[i].style.display = "block";
		}else{
			optList[i].style.display = "none";
		}
	}
}

</script>

<div class = "wrapper">

	<h3>아티스트 등록</h3>
	
	 <form  name=frm action="artist_proc_in" method="post" class = "form" name = "insert" enctype = "multipart/form-data">
	 <ul>
	 	<li>
	 		<p>아티스트 명</p>
	 		 <input type = "text" placeholder="한글명" required name = "namek">
             <input type = "text" placeholder="영문명" required name = "namee">
	 	</li>
	 	
	 	<li>
	 		<p>아티스트 코드</p>
	 		<input type = "text" placeholder="알파벳 대문자2자리"  name = "accode" maxlength = "2">
	 	</li>

	 	
	 	<li>
           	<p>그룹 여부</p>
           	<input type="radio" name="isgroup" value="그룹" id="ismember" checked = "checked" onclick="Radio_OnOff('Radio_On');">그룹
           	<input type="radio" name="isgroup" value="솔로" id="issolo"  onclick="Radio_OnOff('Radio_Off');">솔로
        </li>
        
        
        
       
        	
       
       <div name="form">
       <li  class="membtn" id="offDisplay">
       	 <input name="addButton" type="button" style="cursor:hand" onClick="insRow()" value="추가">
       	<font>*</font>멤버 수만큼 추가버튼을 클릭해보세요.
       	
       	
       	<input type="hidden" value="" name="memnamek">
       	<input type="hidden" value="" name="memnamee">
       	
        <table id="mem_add" width="400" cellspacing="0" border="0">
        <tr>
        
	       <td>
	       
	       <input type = "text" placeholder="한글명"  name = "memnamek">
		   <input type = "text" placeholder="영문명"  name = "memnamee"></td>
		</tr>
        </table>
        	<td align="center">
        		<input type="button" name="button" value="확인" onClick="frmCheck();">
        	</td>
         </li>
        </div>
        
        
        <li>
                <p>이미지</p>
                <input type = "file" name = "piimg" value = "이미지 추가">
        </li>
	 </ul>
	 <input type="reset" value="초기화" id="reset">
     <input type="submit" value="등록" onclick="check_onclick()">
     
	 </form>
<script >
function offDisplay() {
	document.getElementById("offDisplay").style.display="none";
}




function Radio_OnOff(id) {
	  
	 if(id == "Radio_On") {
	      document.all["offDisplay"].style.display = '';         
	   
	 } else {
	      document.all["offDisplay"].style.display = 'none';  // 안보이게
	   }
	}

var oTbl;
function insRow() {
	oTbl = document.getElementById("mem_add");
	var oRow = oTbl.insertRow();
	oRow.onmouseover=function(){oTbl.clickedRowIndex=this.rowIndex};
	var oCell =oRow.insertCell();
	
	var frmTag ="<input type = text placeholder=한글명  name = memnamek > <input type = text placeholder=영문명  name = memnamee>";
	
	
	frmTag += "<input type=button value='삭제' onclick='removeRow()' style='cursor:hand'>";
	oCell.innerHTML = frmTag;
	

}
function removeRow() {
	oTbl.deleteRow(oTbl.clickedRowIndex);
}

function frmCheck() {
	var frm = document.createElement("form")
	
	 for( var i = 0; i <= frm.elements.length - 1; i++ ){
     if( frm.elements[i].name == "addText" ){
         if( !frm.elements[i].value ){
             alert("텍스트박스에 값을 입력하세요!");
                 frm.elements[i].focus();
	 return;
          }
		}
	}
}



</script>	 
	
</div>

<style>
.btn-sm {
	width:8%;
	heigh:5px;
	padding:0;
	}
</style>
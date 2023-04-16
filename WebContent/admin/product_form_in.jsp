<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_inc/inc_admin.jsp" %>
<style>
<%@include file="../css/common.css"%>
<%@include file="../css/init.css"%>
</style>

<%
request.setCharacterEncoding("utf-8");
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

function realprice(value){
	let form = document.insert;
	let price = insert.price.value;
	let dc = insert.dc.value;
	
	document.querySelector("#pricetag").innerHTML = price * (100-dc)/100;
}

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

function able(value){
	let i = parseInt(value.id.substring(6)); //마지막 숫자
	let option = value.checked;
	let stock = document.insert.stock[i+2];
	if(option){
		stock.disabled = false;
	}else{
		stock.disabled = true;
	}
}
</script>

<div class = "wrapper">

    <h3>상품 등록</h3>

    <form action="product_proc_in" method="post" class = "form" name = "insert" enctype = "multipart/form-data">
        <ul>
            <li>
                <p>상품분류</p>
                <div class = "search">
 
                	<select name = "ac" onchange = "select(this.value);">
	                	<option>옵션 선택</option>
	                	<%
						for(int i = 0;i<bigList.size();i++){
							ArtistCode big = bigList.get(i);
						%>
						<option value = "<%=big.getAc_id()%>"><%=big.getAc_name_k() %></option>
						<% }%>
                	</select>
                </div>
         
                <select name = "ap">
                <%
                for(int i = 0;i<smallList.size();i++){ 
				ArtistProduct small = smallList.get(i);
				%>
				<option value = "<%=small.getAp_id()%>"><%=small.getAp_name()%></option>
				<%} %>
                </select>
           
            </li>
            <li>
                <p>상품명</p>
                <input type = "text" placeholder="상품명" required name = "name">
            </li>
            <li>
            	<p>게시여부</p>
            	<input type="radio" name="isview" value="y">게시
            	<input type="radio" name="isview" value="n" checked = "checked">미게시
            </li>
            <li>
                <p>가격</p>
                <input type = "number" name="price" required oninput = "realprice(this.value);">원
            </li>
            <li>
                <p>할인</p>
                <input type = "number" name="dc" value = "0" min = "0" max = "100" oninput = "realprice(this.value);">%
            </li>
            <li>
                <p>판매가</p>
                <div id = "pricetag">0</div>원
            </li>

            <li>
                <p>특별상품</p>
                <input type="radio" name="special" value="n" checked = "checked">신규
                <input type="radio" name="special" value="h">인기
                <input type="radio" name="special" value="m">md추천                
            </li>
            <li>
                <p>판매기간</p>
                <input type="number" name="sdate" placeholder = "YYMMDD"  maxlength = "6" required>
            </li>
            <li>
                <p>옵션</p>
                <div>
                <div>
                <input type = "hidden" name = "option" value = " ">
                <input type = "hidden" name = "stock" value = "0">
                <input class = "option" type="checkbox" name="option" value = "" checked = "checked">
                <label for = "option">없음</label>
                <input type = "number" name = "stock" placeholder = "재고량" value = "0">
                </div>
                <%
                ArtistMember am = null;
                for(int i = 0;i<artistMemberList.size();i++){
                	am = artistMemberList.get(i);
                %>
                   	<div class = "option_list" id = "<%=am.getAc_id()+i%>" >
                   	<input class = "option" type="checkbox" name="option" value="<%=am.getAm_name_k()%>" 
                   		onclick = "able(this);" id = "option<%=i%>">
                   	<label for = "option"><%=am.getAm_name_k() %></label>
                   	<input type = "number" name = "stock" placeholder = "재고량" value = "0" 
                   		disabled = "disabled">
                   	</div>
               <%  }%>
         
            	</div>
            	
            </li>
            <li>
                <p>이미지</p>
                <input type = "file" name = "piimg1" value = "이미지 추가">
            </li>
            <li>
                <p></p>
                <input type = "file" name = "piimg2" value = "이미지 추가">
            </li>
            <li>
                <p></p>
                <input type = "file" name = "piimg3" value = "이미지 추가">
            </li>
            <li>
                <p>상세 설명 이미지</p>
                <input type = "file" name = "pidesc" value = "이미지 추가">
            </li>
            <li>
                <p>상품정보 입력</p>
                <textarea required name="text" id = "text"></textarea>
            </li>
        </ul>
        <input type="reset" value="초기화">
        <input type="submit" value="등록">
    </form>
    
</div>
</body>
</html>

<style>
.search{
	position: relative;
}
.option_list{
	display: none;
	width: 100%;
}
.form > div{
	width: 70%;
}
#text{
	width: 60%;
	height: 20vh;
	margin: 10px;
}
</style>
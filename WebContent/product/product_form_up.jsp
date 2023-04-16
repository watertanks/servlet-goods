<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_inc/inc_admin.jsp" %>
<style>
<%@include file="../css/common.css"%>
<%@include file="../css/init.css"%>
</style>
<%
request.setCharacterEncoding("utf-8");
ProductInfo pi = (ProductInfo)request.getAttribute("pi");
ArrayList<ProductStock> psList = pi.getStockList();
ArrayList<ArtistMember> amList = (ArrayList<ArtistMember>)request.getAttribute("amList");
/*
상품 수정폼
1. 받아오는 기존 상품데이터 pi(상품정보)/psList(상품옵션)

2. 상품수정폼 보내는 목록(request)
상품명 name
가격 price
할인 dc
특별상품 special(n, h, m)
판매기간 sdate(yymmdd)
옵션 배열 (그룹코드 멤버명)
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

function able(value){
	let i = parseInt(value.id.substring(6)); //마지막 숫자
	let option = value.checked;
	let stock = document.insert.stock[i+1];
	if(option){
		stock.disabled = false;
	}else{
		stock.disabled = true;
	}
}
</script>
<div class = "wrapper">

    <h3>상품 등록</h3>

    <form action="product_proc_up" method="post" class = "form" name = "insert" enctype = "multipart/form-data">
        <ul>
            <li>
                <p>상품분류</p>
                <input type = "hidden" name = "piid" value = <%=pi.getPi_id() %>>
         		<div><%=pi.getAc_name() %></div>&nbsp;&nbsp;&nbsp;&nbsp;
         		<div><%=pi.getAp_name() %></div> 
            </li>
            <li>
                <p>상품명</p>
                <input type = "text" placeholder="상품명" required name = "name" value = "<%=pi.getPi_name()%>">
            </li>
            <li>
            	<p>게시여부</p>
            	<input type="radio" name="isview" value="y" 
            	<%if(pi.getPi_isview().equals("y")){%> checked = "checked" <%} %>>게시
            	<input type="radio" name="isview" value="n"  
            	<%if(pi.getPi_isview().equals("n")){%> checked = "checked" <%} %>>미게시
            </li>
            <li>
                <p>가격</p>
                <input type = "number" name="price" required oninput = "realprice(this.value);" value = "<%=pi.getPi_price()%>">원
            </li>
            <li>
                <p>할인</p>
                <input type = "number" name="dc" min = "0" max = "100" oninput = "realprice(this.value);" value = "<%=pi.getPi_dc()%>">%
            </li>
            <li>
                <p>판매가</p>
                <div id = "pricetag">0</div>원
            </li>

            <li>
                <p>특별상품</p>
                <input type="radio" name="special" value="n" 
                <%if(pi.getPi_special().equals("n")){%> checked = "checked" <%} %>>신규
                <input type="radio" name="special" value="h" 
                <%if(pi.getPi_special().equals("h")){%> checked = "checked" <%} %>>인기
                <input type="radio" name="special" value="m" 
                <%if(pi.getPi_special().equals("m")){%> checked = "checked" <%} %>>md추천                
            </li>
            <li>
                <p>판매기간</p>
                <%
                String sdate = pi.getPi_sdate();
                sdate = sdate.substring(2, 10).replaceAll("-", "");
                %>
                <input type="number" name="sdate" placeholder = "YYMMDD"  maxlength = "6" value = "<%=sdate%>" required >
            </li>
            <li>
                <p>옵션</p>
                <div>
                	<input type="hidden" name="option" value = "">
                	<input type = "hidden" name = "stock" value = "0">
                <%
                ProductStock ps = null;
                for(int i = 0;i<psList.size();i++){
                	ps = psList.get(i);
                %>
                   	<div>
                   	<input type="checkbox" name="option" id = "option<%=i %>" onclick = "able(this);" 
                   	value="<%=ps.getPs_am_name()%>">
                   	<label for = "option"><%=ps.getPs_am_name() %></label>
                   	<input type = "number" name = "stock" placeholder = "재고량"  value = "<%=ps.getPs_stock() %>"
                   	disabled = "disabled">
                   	</div>
               <%  }%>
         
            	</div>
            </li>
            <li>
                <p>이미지</p>
                <input type = "hidden" name = "oldpiimg1" value = "<%=pi.getPi_img1() %>">
   				<%if(pi.getPi_img1()!=null && !pi.getPi_img1().equals("")){ %>
   				<img src = "/Goods/<%=pi.getPi_img1() %>">
   				<%} %>
   				<input type = "file" name = "piimg1">
            </li>
            <li>
                <p></p>
                <input type = "hidden" name = "oldpiimg2" value = "<%=pi.getPi_img2() %>">
                <%if(pi.getPi_img2()!=null && !pi.getPi_img2().equals("")){ %>
   				<img src = "/Goods/<%=pi.getPi_img2() %>">
   				<%}%>
                <input type = "file" name = "piimg2">
            </li>
            <li>
                <p></p>
                <input type = "hidden" name = "oldpiimg3" value = "<%=pi.getPi_img3() %>">
                <%if(pi.getPi_img3()!=null && !pi.getPi_img3().equals("")){ %>
   				<img src = "/Goods/<%=pi.getPi_img3() %>">
   				<%}%>
   				<input type = "file" name = "piimg3">
            </li>
            <li>
                <p>상세 설명 이미지</p>
                <input type = "hidden" name = "oldpidesc" value = "<%=pi.getPi_desc() %>">
                <%if(pi.getPi_desc()!=null && !pi.getPi_desc().equals("")){ %>
   				<img src = "/Goods/<%=pi.getPi_desc() %>">
   				<%} %>
                <input type = "file" name = "pidesc">
            </li>
            <li>
                <p>상품정보 입력</p>
                <textarea required name="text" id = "text"><%=pi.getPi_text()%></textarea>
            </li>
        </ul>
        <input type="reset" value="초기화">
        <input type="submit" value="완료">
    </form>
    
</div>
</body>
</html>

<style>
.bigList{
	display: none;
	border: 1px solid black;
	width: 100%;
	position: absolute;
	top: 100%;
	background-color: white;
}
#text{
	width: 60%;
	height: 20vh;
	margin: 10px;
}
img{
	width: 30%;
}
</style>
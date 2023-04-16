<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../_inc/inc_admin.jsp"%>

<%
request.setCharacterEncoding("utf-8");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
ArrayList<ArtistCode> artistList = (ArrayList<ArtistCode>)request.getAttribute("artistList");

int cpage = pageInfo.getCpage();
int psize = pageInfo.getPsize();
int bsize = pageInfo.getBsize();
int rcnt = pageInfo.getRcnt();
int pcnt = pageInfo.getPcnt();
int spage = pageInfo.getSpage();

String ap = request.getParameter("ap");
String orderby = request.getParameter("orderby");
String schtype = request.getParameter("schtype");
%>

<style>
<%@include file="../css/init.css"%>
<%@include file="../css/common.css"%>
@import url('./common.css');
li {list-style: none;}

main { width: 70%;
    margin: 0 auto;}
.content_top {

}
.content_top > h2{
    font-size: 60px;
    text-align: center;
    color: #ddd;
    font-family: 'Merriweather', serif;
    font-family: 'Roboto Slab', serif;
}
.content_top > p {
    font-size: 15px;
}
.artistlist {
    padding-top: 1%;
    

}
.artistlist > ul{
   display: flex;
   flex-wrap: wrap;
   width:100%;


}
.artistlist > ul > li {
    padding: 0 2%;
    text-align: center;
    padding-top: 3%;
    width:25%;
}
.artistlist > ul>li>a {
width: 100%;
}
.artistlist > ul>li>a > img {width:100%;}
.artistlist > ul > li> p > h3 {
  display: none;
}
.paging {
	width: 100%;
	text-align : center;
}
.paging a, .paging p{
	display: inline-block;
	 width: 50px;
}
.btn {
	background-color: #6255f6;
    color: white;
    border: none;
    padding: 10px 20px;
    margin: auto;
    margin-top:20px;
    width:15%;
    text-align : center;
    
    
}
.btn a {
	color : white;
	margin : 0;
	
}
</style>


   

    <main>
    
    <div class="content_top">
        <h2>ARTIST</h2>
        <p>
           <strong>총 "<%out.println(artistList.size());%>"개의 아티스트 팀이 있습니다. </strong>
        </p>

        <span name ="schForm" style="float:right;">
        <form name = "schForm">
            <select name = "orderby">
       		  	<option value = "a" <%if(orderby!=null && orderby.equals("a")){%> selected = "selected" <%} %>>업데이트 순</option>
				<option value = "b" <%if(orderby!=null && orderby.equals("b")){%> selected = "selected" <%} %>>가나다 순</option>
				
				<input type = "submit" class = "button" value = "검색">
            </select>
        </form>
     </span>
    </div>
    <script>
			function init(){
				let schForm = document.schForm;
				schForm.orderby.value = '';
			}
		</script>
    

    <div class = "artistlist">

        <ul class = "artistinfo">
<%

for(int i = 0; i < artistList.size(); i++) {
	System.out.println(artistList.size());
	ArtistCode ac = artistList.get(i);
	String KorName = ac.getAc_name_k();
	String EngName = ac.getAc_name_e();
	
%>
   			
            <li >
            	<a href="product_list?ac=<%=ac.getAc_id()%>"><img src="/goods/artist/artist_img/<%=ac.getAc_img() %>"></a>
				<p>
					<h3>
						<%=KorName %>
						<br>
					</h3><%=EngName %>
				</p>
            </li>
 
		
<%
}
%>	
	
        </ul>

    </div>
    <div class="paging">
	<%if(cpage!=1) {%><a href="artist_list?cpage=1">처음</a>
	<%}else{%><p>처음</p><%} %>
	
	<%if(cpage>1){ %><a href="artist_list?cpage=<%=(cpage-1) %>">이전</a>
	<%}else{%><p>이전</p><%} %>
	
	<%
	for(int i= spage;i<=pcnt && i<=bsize; i++){
		out.println("<a href='artist_list?cpage="+i+"'>"+i+"</a>");
	}
	%>
	
	<%if(cpage<pcnt){ %><a href="artist_list?cpage=<%=(cpage+1) %>">다음</a>
	<%}else{%><p>다음</p><%} %>
	
	<%if(cpage!=pcnt) {%><a href="artist_list?cpage=<%=pcnt%>">마지막</a>
	<%}else{%><p>마지막</p><%} %>

	</div>
	
	</div>
	<div class = "btn">
	<a href = "artist_form_in" class = "button">신규 등록</a>
	</div>
</main>

</body>
</html>
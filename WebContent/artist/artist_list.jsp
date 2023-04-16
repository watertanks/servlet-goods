<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../_inc/inc_head.jsp"%>

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
if(ap==null){ ap = ""; } 
String orderby = request.getParameter("orderby");
if(orderby==null){ orderby = ""; } 
String schtype = request.getParameter("schtype");
if(schtype==null){ schtype = ""; } 

String tmp = "&ap="+ap+"&orderby="+orderby+"&schtype="+schtype;
%>

<style>
<%@include file="../css/common.css"%>
li {list-style: none;}

main { transform: translateY(300px);  width: 70%;
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
.artistlist > ul > li> p > h3 {
  display: none;
}
.paging {
	width: 100%;
	text-align : center;
	margin-top:5%;
}
.paging a, .paging p{
	display: inline-block;
	 width: 50px;
}
</style>

    <main>
 
    <div class="content_top">
        <h2>ARTIST</h2>
        <p>
           <strong>총 "<%=artistList.size()%>"개의 아티스트 팀이 있습니다. </strong>
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
	ArtistCode ac = artistList.get(i);
	String KorName = ac.getAc_name_k();
	String EngName = ac.getAc_name_e();
	
%>
   			
            <li >
            	<a href="product_list?ac=<%=ac.getAc_id()%>&key=<%=ac.getAc_name_k()%>&schtype=a">
               <img src="artist/artist_img/<%=ac.getAc_img() %>"></a>
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
	<%
	
	if(cpage!=1){
		out.println("<a href='artist_list?cpage=1"+tmp+"'>처음</a>");
	}else{ 
		out.println("<p>처음</p>");
	}
	
	if(cpage>1){
		out.println("<a href='artist_list?cpage="+(cpage-1)+tmp+"'>이전</a>");
	}else{ 
		out.println("<p>이전</p>");
	}
	
	for(int i = 1; i<=pcnt;i++){
		if(cpage == i){
			out.println("<p>"+i+"</p>");
		}else{
			out.println("<a href = 'artist_list?cpage="+i+tmp+"'>"+i+"</a>");
		}
	}
	
	if(cpage<pcnt){
		out.println("<a href='artist_list?cpage="+(cpage+1)+tmp+"'>다음</a>");
	}else{ 
		out.println("<p>다음</p>");
	}
	
	if(cpage!=pcnt){
		out.println("<a href='artist_list?cpage="+pcnt+tmp+"'>마지막</a>");
	}else{ 
		out.println("<p>마지막</p>");
	}
	%>
	</div>

	</div>
</main>
</body>
</html>
</body>
</html>
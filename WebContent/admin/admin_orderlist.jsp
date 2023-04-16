<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../_inc/inc_admin.jsp"%>
<%
request.setCharacterEncoding("utf-8");

ArrayList<OrderInfo> orderlist = (ArrayList<OrderInfo>)request.getAttribute("orderList");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
int bsize = pageInfo.getBsize(), cpage = pageInfo.getCpage();
int psize = pageInfo.getPsize(), pcnt = pageInfo.getPcnt();
int spage = pageInfo.getSpage(), rcnt = pageInfo.getRcnt();
String schtype = pageInfo.getSchtype();
String keyword = pageInfo.getKeyword();

String schargs="",args="";
if(schtype != null&& !schtype.equals("")&& keyword != null && !keyword.equals("")){
// 검색 조건(schtype)과 검색어(keyword)가 있으면 검색관련 쿼리스트링 생성
	schargs = "&schtype=" + schtype + "&keyword=" + keyword;
}
args = "&cpage=" + cpage + schargs;

%>

<main>
	 <div class="content">
        	<div>
        		<form name="frnsch" method="get">
				<fieldset>
					<legend>게시판 검색</legend>
					<select name="schtype">
						<option value="">검색조건</option>
						<option value="miid" <%if(schtype.equals("miid")){ %>
							selected="selected" <%} %>>아이디</option>
						<option value="oiid" <%if(schtype.equals("oiid")){ %>
							selected="selected" <%} %>>주문번호</option>
						<option value="piid" <%if(schtype.equals("piid")){ %>
							selected="selected" <%} %>>상품번호</option>
					</select> <input type="text" name="keyword" value="<%=keyword %>" /> <input
						type="submit" value="검색" /> <input type="button" value="전체 글"
						onclick="location.href='admin_order_list'" />
				</fieldset>
				</form>
        	</div>
      <div>
                <%
               
                if(orderlist.size()>0){ // 주문내역이 있다면
                	int num= rcnt -(psize*(cpage-1));
                	for(int i=0;i<orderlist.size();i++){	
                		OrderInfo ol = orderlist.get(i);
                		String oiname = ol.getOi_id();
                		String title= oiname;
                		title = "<a href = 'order_detail?"+"oiid="+ol.getOi_id()+args+"'>"+title+""+"</a>";	
                	%> 
            	<div class = "orderpdt"> <!-- 주문목록 -->
                 	<a href="order_detail" class="img"><img src="<%=ol.getOd_img()%>"/></a> 
                    <div class="orderdt">
                    	<p> 주문일 : <%=ol.getOi_date() %> </p>	
                        <p>	상품명 : <%=title %></p>  
                        <div class="money"><span>수량 : <%=ol.getOd_cnt() %></span><span><%=ol.getOd_price() %> 원</span></div>
                    </div>   
                </div>
                <%
                	}
                }
                %>
            </div>
			
        </div>
</main>
<%@include file="../_inc/inc_foot.jsp"%>
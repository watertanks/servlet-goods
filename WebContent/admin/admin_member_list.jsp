<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "/_inc/inc_admin.jsp" %>
<%
request.setCharacterEncoding("utf-8");

PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
ArrayList<MemberInfo> memberList = (ArrayList<MemberInfo>)request.getAttribute("memberList");

int cpage = pageInfo.getCpage();
int psize = pageInfo.getPsize();
int bsize = pageInfo.getBsize();
int rcnt = pageInfo.getRcnt();
int pcnt = pageInfo.getPcnt();
int spage = pageInfo.getSpage();

String status = request.getParameter("status");
String kind = request.getParameter("kind");
String o = request.getParameter("o");
String key = request.getParameter("key");
if(key==null){ key = ""; } 
String schtype = request.getParameter("schtype");




%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js" ></script>
<style>
<%@include file="../css/init.css"%>
<%@include file="../css/common.css"%>
.main{text-align:center; }
.content-1{margin:0 auto; text-align:center;}
</style>
<main>
    <div class="content-1" style="width:1000px;">
    <form method="GET" action="/goods/member_list">
       <div>
          <select name="kind" >
             <option value="n" <%if(kind!=null && kind.equals("n")){%> selected = "selected" <%} %>>가입유형</option>
             <option id="chkchk" value="a" <%if(kind!=null && kind.equals("a")){%> selected = "selected" <%} %>>내부가입</option>
             <option value="b" <%if(kind!=null && kind.equals("b")){%> selected = "selected" <%} %>>외부가입</option>
          </select>
          <select name="status" >
             <option value="n" <%if(status!=null && status.equals("n")){%> selected = "selected" <%} %>>회원상태</option>
             <option value="a" <%if(status!=null && status.equals("a")){%> selected = "selected" <%} %>>정상</option>
             <option value="b" <%if(status!=null && status.equals("b")){%> selected = "selected" <%} %>>휴면</option>
             <option value="c" <%if(status!=null && status.equals("c")){%> selected = "selected" <%} %>>탈퇴</option>
             <option value="d" <%if(status!=null && status.equals("d")){%> selected = "selected" <%} %>>정지</option>
          </select>
       </div>
       <select name="schtype">
          <option value="n" <%if(schtype!=null && schtype.equals("n")){%> selected = "selected" <%} %>>검색조건</option>
          <option value="a" <%if(schtype!=null && schtype.equals("a")){%> selected = "selected" <%} %>>ID</option>
          <option value="b" <%if(schtype!=null && schtype.equals("b")){%> selected = "selected" <%} %>>이름</option>
          <option value="c" <%if(schtype!=null && schtype.equals("c")){%> selected = "selected" <%} %>>닉네임</option>
       </select>
       <select name="o">
          <option value="n" <%if(o!=null && o.equals("n")){%> selected = "selected" <%} %>>정렬기준</option>
          <option value="a" <%if(o!=null && o.equals("a")){%> selected = "selected" <%} %>>최근 회원가입순</option>
          <option value="b" <%if(o!=null && o.equals("b")){%> selected = "selected" <%} %>>회원가입순</option>
          <option value="c" <%if(o!=null && o.equals("c")){%> selected = "selected" <%} %>>최근 로그인순</option>
          <option value="d" <%if(o!=null && o.equals("d")){%> selected = "selected" <%} %>>로그인순</option>
       </select>
       <div class="schArea" style="text-align: center;"><input type="search" /><input type="submit" value="검색"/></div>
    </form>
    </div>
    <div>
       <table style="text-align:center;">
          <th width="5%" style="background-color: #6255f6; color:white;" >번호</th>
           <th width="15%" style="background-color: #6255f6; color:white;">아이디</th>
           <th width="15%" style="background-color: #6255f6; color:white;">이름</th>
           <th width="15%" style="background-color: #6255f6; color:white;">닉네임</th>
           <th width="10%" style="background-color: #6255f6; color:white;">가입일자</th>
           <th width="*" style="background-color: #6255f6; color:white;">이메일</th>
           <th width="15%" style="background-color: #6255f6; color:white;">최종 로그인 날짜</th>
           <th width="10%" style="background-color: #6255f6; color:white;">회원상태</th>
<%if(memberList.size()==0) {%>
   <p>검색 결과가 없습니다</p>
   <%}else {%>
   <!-- 레코드 한줄 시작 -->
<%
MemberInfo mi = null;
int num = rcnt-(psize*(cpage-1));
for(int i = memberList.size()-1; i >= pcnt-2; i--){ 
mi = memberList.get(i);
//System.out.println(mi.getMi_id()+"Null이니?");
%>
      <tr id="recode" onclick="detail('<%=mi.getMi_id() %>');" onclick="scaleOpt('50');">
            <td><%=num %></td>
          <td><%=mi.getMi_id() %></td>
          <td><%=mi.getMi_name() %></td>
          <td><%=mi.getMi_nickname() %></td>
          <td><%=mi.getMi_joindate() %></td>
          <td><%=mi.getMi_email() %></td>
          <td><%=mi.getMi_lastlogin() %></td>
          <td><%=mi.getMi_status() %></td>
      </tr>
   <%num--;} %>
   <!-- 레코드 한줄 끝 -->
   </div>
   <div class="paging" style="display:flex; width:1000px; margin: 0 auto; text-align:center; align-items: center; justify-content: space-evenly;" >
   <%if(cpage!=1) {%><a href="member_list?cpage=1">처음</a>
   <%}else{%><p>처음</p><%} %>
   
   <%if(cpage>1){ %><a href="member_list?cpage=<%=(cpage-1) %>">이전</a>
   <%}else{%><p>이전</p><%} %>
   
   <%
   for(int i= spage;i<=pcnt && i<=bsize; i++){
      out.println("<a href='member_list?cpage="+i+"'>"+i+"</a>");
   }
   %>
   
   <%if(cpage<pcnt){ %><a href="member_list?cpage=<%=(cpage+1) %>">다음</a>
   <%}else{%><p>다음</p><%} %>
   
   <%if(cpage!=pcnt) {%><a href="member_list?cpage=<%=pcnt%>">마지막</a>
   <%}else{%><p>마지막</p><%} %>

   </div>
<% } %>
</table>
<!-- member detail table -->
<!-- 
obj[0] = "";
obj[1] = "ID";
obj[2] = "PW";
obj[3] = "성별";
obj[4] = "생년월일";
obj[5] = "이름";
obj[6] = "닉네임";
obj[7] = "이메일";
obj[8] = "핸드폰번호";
obj[9] = "보유포인트";
obj[10] = "문의내역";
obj[11] = "쿠폰수";
obj[12] = "마지막 로그인날짜";
obj[13] = "회원가입날짜";
obj[14] = "회원상태";
obj[15] = "내부가입 OR 외부가입";
 -->
<table style="border:1px solid #000; width:1000px; text-align:center; margin: 0 auto;" id="detail_table">
</table>

<style>
table{border:1px solid #000; margin: 0 auto;}
td{border:1px solid #000;}
tr{border:1px solid #000;}
#detail_table{margin-bottom:50px;}
#recode {cursor: pointer;} 
#recode:hover {
   background-color: #f3f3f3;
}
</style>
</body>
</main>
<script>
function detail(miid){
   $.ajax({
      type : "POST",
      url : "member_detail",
      datatype : "text",
      data : {"miid" : miid},
      success : function(data){
         var obj = data.split(",");
         console.log(obj);
         str = '<table style="border:1px solid #000; width:1000px; text-align:center; margin: 0 auto;" id="detail_table">';
         str += '<tr>';
         str += '<tr><td width="15%"style="background-color: #6255f6; color:white;">ID</td>   <td width="35%" id="detail_id">' +obj[1]+ '</td>   <td width="15%"style="background-color: #6255f6; color:white;">회원상태</td>   <td width="35%" >' +obj[14]+ '</td>   </tr>';
         str += '<tr><td width="15%"style="background-color: #6255f6; color:white;">닉네임</td>   <td width="25%" >' +obj[6]+ '</td>            <td width="25%"style="background-color: #6255f6; color:white;">문의내역</td>   <td width="25%" >' +obj[10]+ '</td></tr>';
         str += '<tr><td width="15%"style="background-color: #6255f6; color:white;">가입경로</td>   <td colspan="3" width="*" >' +obj[15]+ '</td>';
         str += '<tr><td width="15%"style="background-color: #6255f6; color:white;">이름</td><td width="35%" >' +obj[5]+ '</td> </td>          <td width="15%"style="background-color: #6255f6; color:white;">가입일</td> <td width="25%" >' +obj[13]+ '</td></tr>';
         str += '<tr><td width="15%"style="background-color: #6255f6; color:white;">E-mail</td>   <td width="35%" >' +obj[7]+ '</td>             <td width="15%"style="background-color: #6255f6; color:white;">휴대폰</td>   <td width="25%" >' +obj[8]+ '</td>';
         str += '<tr><td width="15%"style="background-color: #6255f6; color:white;">주소</td><td colspan="3" width="35%" >' +obj[0]+ '</td>';
         str += '<tr><td width="15%"style="background-color: #6255f6; color:white;">최종수정일</td>   <td width="35%" >' +obj[1]+ '</td>          <td width="15%"style="background-color: #6255f6; color:white;">최종방문일</td>   <td width="25%" >' +obj[1]+ '</td>';
         str += '</tr>';
         str += '</table>';
         str += '<div class="btnArea" style="width:1000px;margin:0 auto; display:flex; flex-direction: row-reverse; margin-bottom:100px;">';
         str += '<input id="btn" type="button" onclick="isUp()" value="수정하기"/>';
         str += '</div>';
         $('.btnArea').remove();
         $('#btn').remove();
         $('#detail_table').remove();
         $('html').append(str);
      },      
   });
}

function isUp(){
   confirm('회원정보를 수정하시겠습니까?');
   if(confirm == true){
      out.println("<script>");
   }
}
</script>

</html>
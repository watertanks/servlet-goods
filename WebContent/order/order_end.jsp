<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../_inc/inc_head.jsp" %>
<%
OrderInfo oi = (OrderInfo)request.getAttribute("orderInfo");
%>
<style>
main{    
	max-width: 100%;
	margin: 0 auto;
	position: relative;
	top: 220px;
}
</style>
<main>
	<form>
		<div>
			<h2> 주문 완료 </h2>
			<p>구매해 주셔서 감사합니다.</p>
		</div>
		<div>
			<p><%=loginInfo.getMi_name() %>님 구매해 주셔서 감사합니다</p>
		</div>
		<div>
			<p>고객님이 주문하신 주문번호는</p>
			<p><%=oi.getOi_id() %>입니다</p>
			<p>주문내역 확인은 마이페이지의
			“주문배송내역” 에서 하실 수 있습니다</p>
		</div>
		<div>
		<input type="button" value="이전" onclick="" ><input type="button" value="주문내역확인" onclick="location.href='order_detail?oiid=<%=oi.getOi_id() %>'" ></div>
	</form>
</main>
<%@include file = "../_inc/inc_foot.jsp" %>


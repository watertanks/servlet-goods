<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "_inc/inc_head.jsp" %>

<style>
    .bor {border: 1px solid #000;position: absolute; width:250px; height:250px; margin: 0 auto; top: 30%; left: 40%;}
    .frm { display: flex; flex-direction: column; }
    .frm p { align-items: center; text-align: center;}
</style>
<body>
    <div class="bor">
        <form class="frm">
            <p>기존 비밀번호 <input type="password" ></p>
            <p>새 비밀번호 <input type="password" ></p>
            <p>새 비밀번호 확인<input type="password" ></p>
        </form>
    </div>
</body>
</html>
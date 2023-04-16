<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "/_inc/inc_head.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/common.css" rel="stylesheet">
    <link href="mypage.css" rel="stylesheet">
    <title>Document</title>
</head>
<body>
    <header class="header" id="header" style="position :relative;">
        <div class="header-main">
            
            <div class="header-middle">
                <div class="header-3rd">
                    
                    <div class="header-login">
                        <a href="">로그인</a>
                        <a href="">회원가입</a>
                        <a href="">아이디 및 비밀번호찾기</a>
                    </div>
                    <form class="header-sch">
                        <select>
                            <option value="artist">ARTIST</option>
                            <option value="album">ALBUM</option>
                            <option value="etc">ETC</option>
                        </select>
                        <input type="text" name="schbar" placeholder="검색" autocomplete="off">
                        <input type="submit" name="schsubmit" value="search" >
                    </form>
                </div>
                <div class="header-2rd">
                    
                  
                    <div class="header-left">
                        <a href="./index.html"><img src="/img/main1/logo.png" alt="MainLogo"></a>
                    </div>
                </div>
                <div class="header-1st">
                    <div class="header-ctgr">
                        
                        
                            <div>
                                <a href="../new.html">ARTIST</a>
                            </div>
                            <div>
                                <a href="">MD</a>
                            </div>
                            <div>
                                <a href="../artist/artist.html">COMING SOON</a>
                            </div>
                            <div>
                                <a href="./event_list.html">EVENT</a>
                            </div>
                            <div>
                                <a href="./notice_list.html">CUSTOMER</a>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="wrapper">
        <h3>마이페이지 > 내정보수정</h3>

       <div class="menu">
                  <p>내 정보</p>
	           <a href="/goods/my_page">내정보</a>
	           <a href="change_pw_form">비밀번호변경</a>
	           <p>주문정보</p>
	           <a href="/goods/cart_view">장바구니</a>
	           <a href="/goods/order_list">주문내역</a>
	           <p>포인트/쿠폰</p>
	           <a href="/goods/point_list">포인트내역</a>
	           <a href="/goods/coupon_list">쿠폰함</a>
	           <p>기타</p>
	           <a href="mantoman_list.html">문의내역</a>
	           <a href="member_out_form.html">회원탈퇴</a>
        </div>

        <div class="content">
            <form action="" method="post" name="myinfoForm" class="form">
                <ul>
                    <li>
                        <p>이름</p><input type="text" required>
                    </li>
                    <li>
                        <p>ID</p><span>gildong1234</span>
                    </li>
                    <li>
                        <p>닉네임</p><input type="text" required>
                    </li>
                    <li>
                        <p>이메일</p><input type="text" required>@
                        <select onchange="">
                            <option>직접입력</option>
                            <option>naver</option>
                            <option>gmail</option>
                        </select>
                        <input type="text" disabled>
                    </li>
                    <li>
                        <p>연락처</p>
                        <select>
                            <option>010</option>
                            <option>011</option>
                            <option>016</option>
                            <option>019</option>
                        </select>-
                        <input type="number" required maxlength="4">-
                        <input type="number" required maxlength="4">
                    </li>
                    <li>
                        <p>주소</p><input type="text" readonly>
                        <button onclick="">주소검색</button>
                    </li>
                    <li>
                        <p>상세주소</p><input type="text" required>
                    </li>
                    <li>
                        <p>생년월일</p><span>1995-10-02</span>
                    </li>
                    <li>
                        <p>개인정보제공동의</p>
                        <input type="checkbox" value="y" name="agree" checked="checked">
                        <label for="agree">동의</label>
                        <input type="checkbox" value="n" name="agree">
                        <label for="agree">미동의</label>
                    </li>
                </ul>
                <!-- <div class="btn">
                    <input type="reset" class="button" value="초기화">
                    <input type="submit" class="button" value="완료">
                </div> -->
            </form>
            <div class="btn">
                <a href = "myinfo_form.html"><button class="button">초기화</button></a>
                <a href = "myinfo_detail.html"><button class="button">완료</button></a>
            </div>
        </div>
        
    </div>
</body>
</html>
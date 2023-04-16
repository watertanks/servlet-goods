<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "/_inc/inc_head.jsp" %>

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
        <h3>마이페이지 > 쿠폰함</h3>

        <div class="menu">
            <p>내 정보</p>
            <a href="myinfo_detail.html">내정보</a>
            <a href="change_pw_form.html">비밀번호변경</a>
            <p>주문정보</p>
            <a href="#">장바구니</a>
            <a href="#">주문내역</a>
            <p>포인트/쿠폰</p>
            <a href="point_list.html">포인트내역</a>
            <a href="coupon_list.html">쿠폰함</a>
            <p>기타</p>
            <a href="mantoman_list.html">문의내역</a>
            <a href="member_out_form.html">회원탈퇴</a>
        </div>

        <div class="content">

            <div class="schtype">
                <button onclick="" class="button">사용가능</button>
                <select>
                    <option>종류별 검색</option>
                    <option>무료배송</option>
                    <option>10퍼센트 할인</option>
                    <option>5퍼센트 할인</option>
                </select>
            </div>

            <table class="list">
                <tr>
                    <th width = 20%>쿠폰</th>
                    <th width = "*">쿠폰번호</th>
                    <th width = 15%>사용기한</th>
                </tr>
            </table>

            <script>
                let list = document.querySelector(".list");
                let li = "";
                for(let i = 0;i<10;i++){
                    li+="<tr><td>무료배송</td><td class='detail'>2N1754841</td><td>2023-03-01</td></tr>";
                }
                list.innerHTML+=li;
            </script>

            <div class="paging">
                <a href = "#">처음</a>
                <a href = "#">이전</a>
                <span></span>
                <a href = "#">다음</a>
                <a href = "#">마지막</a>
            </div>

            <script>
                let page = document.querySelector(".paging > span");
                li = "";
                for(let i = 1;i<=10;i++){
                    li+="<a href = '#'>&nbsp;"+i+"&nbsp;</a>";
                }
                page.innerHTML+=li;
            </script>
        </div>
    </div>
</body>
</html>
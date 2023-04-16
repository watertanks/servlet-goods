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
        <h3>마이페이지 > 회원탈퇴</h3>
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
            <form action="" method="post" name="outForm" class="form">
                <ul>
                    <li>
                        <p>탈퇴사유</p>
                        <select>
                            <option>사용안함</option>
                            <option>사용하기 싫음</option>
                            <option>귀찮음</option>
                        </select>
                    </li>
                    <li>
                        <p>상세내용</p>
                        <textarea></textarea>
                    </li>
                    <li>
                        <p>ID</p>
                        <span>gildong1234</span>
                    </li>
                    <li><p>비밀번호</p>
                        <input type = "password" required>
                    </li>
                    <li>
                        <span>주의: 개인정보 파기 및 복구 불가</span>
                    </li>
                </ul>
                <!-- <div class="btn">
                    <input type = "reset" class="button" value="취소하기">
                    <input type = "submit" class="button" value="탈퇴하기">
                </div> -->
            </form>
            <div class="btn">
                <a href="#"><button  class="button">취소하기</button></a>
                <button class="button" onclick="alert('회원탈퇴 성공'); location.href = '../index.html';">탈퇴하기</button>
            </div>
        </div>
    </div>
</body>
</html>
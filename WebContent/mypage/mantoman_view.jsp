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

    <style>
        .list td{
            text-align: left;
        }
    </style>

    <div class="wrapper">
        <h3>마이페이지 > 내 문의내역</h3>
        <div class="menu">
            <p>내 정보</p>
            <a href="#">정보 수정</a>
            <a href="#">비밀번호변경</a>
            <p>주문정보</p>
            <a href="#">장바구니</a>
            <a href="#">주문내역</a>
            <p>포인트/쿠폰</p>
            <a href="#">포인트내역</a>
            <a href="#">쿠폰함</a>
            <p>기타</p>
            <a href="#">문의내역</a>
            <a href="#">회원탈퇴</a>
        </div>
        
        <div class="content">
            <div class="article">
                <h4>[배송/환불] 상품이 배송되지 않았습니다</h4>
                <h5><p>gildong1234</p> <span>2023-01-01</span></h5>
                <div class="text">
                    <figure>
                        <img src="../img/goods/ive.png">
                    </figure>
                    <p>
                    Lorem ipsum dolor sit amet. <br>
                    Vel vitae tempora ad earum ipsa et reprehenderit autem cum galisum totam et quaerat optio! <br>
                    Qui quasi sint in expedita laudantium et nostrum praesentium eos quia quos sed inventore 
                    laudantium est corporis facilis.<br>
                    Aut aliquam odio qui odio nemo sit galisum doloremque. <br>
                    Et assumenda maxime et Quis saepe ut dicta autem. <br>
                    Non unde quasi quo voluptas sint et provident voluptas in reiciendis harum non quia ratione ut 
                    autem dolores et quod sunt. <br>
                    Est maxime distinctio et vitae sequi ea quidem nobis aut neque corporis 
                    ad repudiandae architecto qui quidem dolorum.  <br>
                </p>

                </div>
            </div>

            <table class="list">
                <tr>
                    <td width = "15%">관리자</td>
                    <td width = "*">Eos ipsum maiores nam tempora porro eos possimus enim et consequatur neque ex quibusdam nesciunt.  <br>
                        Qui enim quia qui sequi quia et nostrum aspernatur aut molestiae ipsam.  <br>
                        Sed molestias quia ut distinctio fugiat et laudantium consequatur.</td>
                </tr>
                <tr>
                    <td width = "15%">관리자</td>
                    <td width = "*">Eos ipsum maiores nam tempora porro eos possimus enim et consequatur neque ex quibusdam nesciunt.  <br>
                        Qui enim quia qui sequi quia et nostrum aspernatur aut molestiae ipsam.  <br>
                        Sed molestias quia ut distinctio fugiat et laudantium consequatur.</td>
                </tr>
            </table>
            <div class="btn">
                <a href="mantoman_list.html"><button  class="button">목록으로</button></a>
             </div>
        </div>
        
    </div>
</body>
</html>
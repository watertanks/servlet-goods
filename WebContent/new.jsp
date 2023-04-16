<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file = "/_inc/inc_head.jsp" %>


    <link href="./css/new.css" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Merriweather:ital@0;1&display=swap" rel="stylesheet">
</head>
<body>
    
    <main>
    <div class="content_top">
        <h2>ARTIST</h2>
        <p>
           <strong>전체 "8"개의 아티스트 팀이 있습니다. </strong>
        </p>
        <span style="float:right;">
            <select name = "artist_array">
                <option value = "update" selected>업데이트 순</option>
                <option value = "가나다" >가나다 순</option>
            </select>
     	</span>
    </div>

    <div class = "artistlist">
        <ul>
            <li>
                <a href="#"><img src="./img/artist_adle.png" alt=""</a>
                <p>
                    <h3>(여자)아이들
                        <br>(G)I-DLE
                    </h3>
                </p>

            </li>
            <li>
                <a href="#"><img src="./img/artist_shinee.png" alt=""</a>
                <p>
                    <h3>샤이니
                        <br>SHINee
                    </h3>
                </p>
            </li>
            <li>
                <a href="#"><img src="./img/artist_red.png" alt=""</a>
                <p>
                    <h3>레드벨벳
                        <br>Red Velvet
                    </h3>
                </p>
            </li>
            <li>
                <a href="#"><img src="./img/artist_bts.png" alt=""</a>
                <p>
                    <h3>방탄소년단
                        <br>BTS
                    </h3>
                </p>
            </li>
            <li>
                <a href="#"><img src="./img/artist_ive.png" alt=""</a>
                <p>
                    <h3>아이브
                        <br>IVE
                    </h3>
                </p>
            </li>
            <li>
                <a href="#"><img src="./img/artist_god.png" alt=""</a>
                <p>
                    <h3>지오디
                        <br>GOD
                    </h3>
                </p>
            </li>
            <li>
                <a href="#"><img src="./img/artist_itzy.png" alt=""</a>
                <p>
                    <h3>있지
                        <br>ITZY
                    </h3>
                </p>
            </li>
            <li>
                <a href="#"><img src="./img/artist_02.png" alt=""</a>
                <p>
                    <h3>영이
                        <br>YOUNG2
                    </h3>
                </p>
            </li>

        </ul>

    </div>
</main>
</body>
</html>
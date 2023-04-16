package ctrl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;


@WebServlet("/member_list")
public class MemberListCtrl extends HttpServlet {
   private static final long serialVersionUID = 1L;
    public MemberListCtrl() {
        super();
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
      
      int cpage = 1, psize = 20, bsize = 10, rcnt = 0, pcnt = 0, spage = 0;
      //   현재페이지       페이지크기         블록크기           전체회원수      전체페이지수  시작페이지번호
      
      if(request.getParameter("cpage")!=null) {
         cpage = Integer.parseInt(request.getParameter("cpage"));
      }
      /*where: db검색 조건
      * 가입유형, 회원상태, 검색어 
      */
      String where ="";
      String kind = request.getParameter("kind");       //가입유형
      String status = request.getParameter("status");    //회원상태
      String key = request.getParameter("key");          //검색어
      String schtype = request.getParameter("schtype");    //검색조건(아이디, 이름, 닉네임 등)
      String o = request.getParameter("o");
      
       // 가입유형 부분
      if(kind == null) kind = "n";
      if(kind.equals("n")) {
         where += " where mi_kind = ('a'or'b') ";
      } else if(kind.equals("a")){
         where += " where mi_kind ='"+kind+"' ";
      } else {
         where += " where mi_kind ='"+kind+"' ";
      }
      
      // 회원상태부분
      if(status == null) status = "n";
      if(status.equals("a")) {      // 정상회원
         where += " and mi_status = '"+status+"' ";
      }else if(status.equals("b")) {  // 휴면계정
         where += " and mi_status = '"+status+"' ";
      }else if(status.equals("c")) {  // 탈퇴계정
         where += " and mi_status = '"+status+"' ";
      }else if(status.equals("d")) {  // 정지계정
         where += " and mi_status = '"+status+"' ";
      } else {
         where += " and mi_status = ('a' or 'b' or 'c' or 'd') ";   // 기본값
      }
      
      // 검색어 부분
      if(key==null)key ="n";
       if(key != null && !key.equals("")) {
          key = key.trim();
          if(schtype!=null && schtype.equals("a")) {            // 검색조건이 id 이면
             where += " and mi_id like '%"+key+"%' ";
          }else if(schtype!=null && schtype.equals("b")) {      // 검색조건이 이름 이면
             where += " and mi_name like '%"+key+"%' ";   
          }else if (schtype!=null && schtype.equals("c")) {      // 검색조건이 닉네임 이면
             where += " and mi_nickname like '%"+key+"%' ";
          }else if (schtype==null || schtype.equals("n")) {      // 검색조건 없을때
             where +=" ";
          }
       }
       
       // 정렬부분
       String orderby = ""; //정렬조건
       //a: 기본값(회원가입 최신순) b: 등록순(회원가입순) c: 로그인순(최신순) d: 로그인순
       if(o==null||o.equals("")) { o = "a"; }
       if(o!=null && !o.equals("")) {
          
         switch(o) {
         case "a": 
            orderby += " order by mi_joindate "; break;
         case "b": 
            orderby += " order by mi_joindate desc "; break;
         case "c": 
            orderby += " order by mi_lastlogin "; break;
         case "d": 
            orderby += " order by mi_lastlogin desc "; break;
         case "n":
            orderby = " order by mi_joindate "; break; //기본정렬은 최근 회원가입순
         }
       }
      AdminMemberListSvc adminMemberListSvc = new AdminMemberListSvc();
      
      rcnt = adminMemberListSvc.getMemberCount(where); //검색된 회원 수
      System.out.println(rcnt);
      ArrayList<MemberInfo> memberList = adminMemberListSvc.getMemberList(cpage, psize, where, orderby);
      //검색된 회원 중 현재페이지에서 보여줄 목록
      
      pcnt = rcnt/psize;
      if(rcnt%psize > 0) { pcnt++; } //전체 페이지수
      spage = (cpage -1)/psize * psize+1; //블록시작 번호
            
      PageInfo pageInfo = new PageInfo(); //페이지 정보 저장
      pageInfo.setBsize(bsize);
      pageInfo.setCpage(cpage);
      pageInfo.setRcnt(rcnt);
      pageInfo.setPcnt(pcnt);
      pageInfo.setPsize(psize);
      pageInfo.setSpage(spage);
      
      //전달할 정보 목록: 페이징, 회원리스트
      request.setAttribute("pageInfo", pageInfo);
      request.setAttribute("memberList", memberList); 
      
      HttpSession session = request.getSession();
      AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");
      
      
      if(adminInfo!=null) {
         RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin_member_list.jsp");
         dispatcher.forward(request, response);
      } else {
         response.setContentType("text/html; charset=utf-8");
         PrintWriter out = response.getWriter();
         out.println("<script>");
         out.println("alert('잘못된 접근입니다.');");
         out.println("history.back();");
         out.println("</script>");
      }
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
      
   }
   

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
   }
}
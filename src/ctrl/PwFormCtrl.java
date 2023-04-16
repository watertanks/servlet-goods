package ctrl;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/pw_form")
public class PwFormCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PwFormCtrl() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		
		
		if (loginInfo == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비정상적 접근입니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
		
		
		String uid = loginInfo.getMi_id();
	      String pwd = request.getParameter("upw").trim();
	      System.out.println(uid);
	      System.out.println(pwd);
	      
	      String select = "select a.mi_pw, b.* ";
	      String from = " from t_member_info a, t_member_addr b ";
	      String where = " where a.mi_id = b.mi_id and a.mi_id = '"+uid+"' and a.mi_pw = '"+pwd+"' ";
	      System.out.println(select + from + where);
	      
	      PwFormSvc pwFormSvc = new PwFormSvc();
	      MemberAddr memberAddr = pwFormSvc.checkPw(select + from + where);

	      response.setContentType("text/html; charset=utf-8");
	      PrintWriter out = response.getWriter();
	      System.out.println("오류확인중"+memberAddr);
	      if(memberAddr != null) {
	         HttpSession session2 = request.getSession();
	         session2.setAttribute("memberAddr", memberAddr);
	         
	         RequestDispatcher dispatcher = request.getRequestDispatcher("/mypage/myinfo_detail.jsp");
	            dispatcher.forward(request, response);
	         
	      } else {
	         out.println("<script>");
	         out.println("alert('비밀번호가 틀렸습니다.');");
	         out.println("history.back();");
	         out.println("</script>");
	         out.close();
	      }
	   }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	}
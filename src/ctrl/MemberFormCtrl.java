package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import vo.*;

@WebServlet("/member_form")
public class MemberFormCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberFormCtrl() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
		if(loginInfo != null) {
			out.println("<script>");
			out.println("alert('로그아웃 후 이용 하실 수 있습니다.');");
			out.println("history.back();");
			out.println("<script>");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member/member_in.jsp");
			dispatcher.forward(request, response);
		}
	}


}

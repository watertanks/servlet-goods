package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import vo.*;
import svc.*;

@WebServlet("/my_page")
public class MypageCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MypageCtrl() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8");
	PrintWriter out = response.getWriter();
	
	HttpSession session = request.getSession();
	MemberInfo loginInfo = (MemberInfo)session.getAttribute("loginInfo");
	
	
	if (loginInfo == null) {
		out.println("<script>");
		out.println("alert('비정상적 접근입니다');");
		out.println("history.back();");
		out.println("</script>");
		out.close();
	} else {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mypage/check_pw_form.jsp");
	      dispatcher.forward(request, response);
	}
	
	}

}

package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/login")
public class LoginFormCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginFormCtrl() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid").trim().toLowerCase();
		String pwd = request.getParameter("pwd").trim();
		String url = request.getParameter("url").replace('$', '&');
		
		LoginFormSvc loginFormSvc = new LoginFormSvc();
		MemberInfo loginInfo = loginFormSvc.getLoginInfo(uid, pwd);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (loginInfo != null) {	
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);

			out.println("<script>");
			out.println("location.replace('" + url + "');");
			out.println("</script>");
			out.close();
		} else {	
			out.println("<script>");
			out.println("alert('아이디 혹은 비밀번호가 틀렸습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}
}


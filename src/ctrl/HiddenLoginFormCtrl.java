package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import svc.LoginFormSvc;
import vo.MemberInfo;


@WebServlet("/hidden_login")
public class HiddenLoginFormCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HiddenLoginFormCtrl() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email").toLowerCase();

		LoginFormSvc loginFormSvc = new LoginFormSvc();
		MemberInfo loginInfo = loginFormSvc.getHiddenLoginInfo(email);

		if (loginInfo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", loginInfo);
			out.println("<script>");
			out.println("location.href='/goods/index.jsp';");
			out.println("</script>");
			out.close();
		} else {	
			out.println("alert('로그인중 에러발생');");
			out.println("history.back();");
			out.close();
		}
		
	}

}

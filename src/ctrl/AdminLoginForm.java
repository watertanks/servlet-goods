package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/admin_login_form")
public class AdminLoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLoginForm() {  super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("adminInfo")!=null) {
			//현재 로그인 중
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href = 'admin_index.jsp';");
			out.println("</script>");
			out.close();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin_login_form.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

package ctrl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;
import vo.*;

@WebServlet("/admin_login")
public class AdminLoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminLoginCtrl() {super();}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid").trim().toLowerCase();
		String pwd = request.getParameter("pwd").trim();
		String url = request.getParameter("url").replace('$', '&');
		
		AdminLoginSvc adminLoginSvc = new AdminLoginSvc();
		AdminInfo adminInfo = adminLoginSvc.getAdminInfo(uid, pwd);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(adminInfo != null) { 
			int newMemberCnt = 0;
			int allMemberCnt = 0;
			HttpSession session = request.getSession();
			session.setAttribute("adminInfo", adminInfo);
			
			newMemberCnt = adminLoginSvc.getNewMember();
			allMemberCnt = adminLoginSvc.getAllMember();
			
			request.setAttribute("newMemberCnt", newMemberCnt);
			request.setAttribute("allMemberCnt", allMemberCnt);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/admin_index.jsp");
			dispatcher.forward(request, response);
			
			out.println("<script>");
			out.println("location.replace('"+url+"');"); 
			out.println("</script>");
			out.close();
		}else { 
			out.println("<script>");
			out.println("alert('아이디와 비밀번호가 틀렸습니다');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		}
	}

}

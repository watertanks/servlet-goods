package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;

@WebServlet("/dupId")
public class DupId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DupId() { super(); }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uid = uid = request.getParameter("uid").trim().toLowerCase();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(uid != null) {
			try {
				ChkDupSvc chkDupSvc = new ChkDupSvc();
				int result = chkDupSvc.chkDupId(uid);
				out.println(result);
			} catch(Exception e) {
				e.printStackTrace();
				out.println("chkDupCtrl클래스의 uid부분 에러발생");
			}
		}
	}
}


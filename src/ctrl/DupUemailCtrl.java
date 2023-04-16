package ctrl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import svc.*;

@WebServlet("/dupUemail")
public class DupUemailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DupUemailCtrl() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("e1"));
		System.out.println(request.getParameter("e3"));
		String uemail = request.getParameter("e1").trim() + "@" + request.getParameter("e3").trim();
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			ChkDupSvc chkDupSvc = new ChkDupSvc();
			int result = chkDupSvc.chkDupUemail(uemail);
			out.println(result);
		} catch(Exception e) {
			e.printStackTrace();
			out.println(1);
		}
	}
}


